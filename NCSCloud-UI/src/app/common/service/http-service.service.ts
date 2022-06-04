import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { DataValidatorService } from './data-validator.service';
import { EventService } from './event.service';
import { GlobalConstants } from '../GlobalConstants ';

/**
 * Makes HTTP request web server for the application 
 */
@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private router: Router,
    private httpClient: HttpClient,
    private dv: DataValidatorService,
    private eService: EventService) {
  }

  /**
   * Get common HTTP request headers 
   */
  getHeader() {

    let skeystr = localStorage.getItem(GlobalConstants.getKey('skey'));
    let appIdstr = localStorage.getItem(GlobalConstants.getKey('appId'));
    let skey = "0";
    let appId = "0";
    if (this.dv.isNotNullObject(appIdstr)) {
      appId = appIdstr;
    }

    if (this.dv.isNotNullObject(skeystr)) {
      skey = skeystr;
    }

    let httpOptions = {
      headers: new HttpHeaders({
        "withCredentials": "true",
        "skey": skey,
        "appId": appId
      })
    };
    return httpOptions;
  }

  /**
   * Send HTTP GET request and call callback method after response is received. 
   * 
   * @param endpoint 
   * @param callback 
   */
  get(endpoint, callback) {

    endpoint = this.urlRewrite(endpoint); 

    return this.httpClient.get(endpoint, this.getHeader()).subscribe(
      (data) => {
        callback(data);
      },
      (data) => {
        this.httpError(data);
        callback(data, true);
      }
    );;
  }

  /**
   * Send HTTP POST request and call callback method after response is received. 
   *
   * @param endpoint 
   * @param bean 
   * @param callback 
   */
  post(endpoint, bean, callback) {
    let _self = this;
    endpoint = this.urlRewrite(endpoint); 
    return this.httpClient.post(endpoint, bean, this.getHeader()).subscribe((data) => {
      callback(data);
    }, (data) => {
      _self.httpError(data);
      callback(data, true);
    });
  }


  /**
   * Send HTTP POST for multipart form data with attached file.
   * 
   * @param endpoint 
   * @param bean 
   * @param file 
   * @param callback 
   */
  postMutipart(endpoint, bean, file, callback) {

    var fd = new FormData();
    //Check if it is file array then attach mutiple files
    if(file.length){
      let i = 0;
      for (i = 0; i < file.length; i++) {
        fd.append('file', file[i]);
      }
    }else{ //Else attache single file
    fd.append('file', file);
    }
    for (var key in bean) {
      if (bean.hasOwnProperty(key)) {
        console.log('Property -->', key, bean[key]);
        if (bean[key]) {
          fd.append(key, bean[key]);
        }
      }
    }

    endpoint = this.urlRewrite(endpoint); 

    return this.httpClient.post(endpoint, fd, this.getHeader()).subscribe((data) => {
      callback(data);
    }, (data) => {
      this.httpError(data);
      callback(data, true);
    });
  }

  /**
   * Rewite URL and append SessionID as header
   * @param endpoint 
   */
  urlRewrite(endpoint){
    let sid = localStorage.getItem( GlobalConstants.getKey('sessionId'));
    if(!sid){
      sid = "0";
    }
    endpoint =  endpoint + ";jsessionid=" + sid;
    return endpoint;
  }

  /**
   * Raise error event 
   * 
   * @param response data 
   */
  httpError(data){
    console.log("Http Error: [" +data.status + "]  ", data);
    if(data.status == 0){  
      this.eService.appErrorOccured("Server seems down, check internet connection");
    }else if(data.status == 401){ //Session expired 
      this.eService.sessionEnd("OOPS your session is expired");
    }else{
      this.eService.appErrorOccured("Server Error:  [" +data.status + "] " + data.message);
    }
  }
}
