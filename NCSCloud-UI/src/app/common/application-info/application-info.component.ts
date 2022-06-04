import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service/service-locator.service';

@Component({
  selector: 'app-application-info',
  templateUrl: './application-info.component.html',
  styleUrls: ['./application-info.component.css']
})
export class ApplicationInfoComponent extends BaseListCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.APPINFO, locator, route);
  }


  populateForm(form, data) {
    form.id = data.id;
    form.name = data.name;
  }

  public application;
  public session;
  public request;

  //Override
  ngOnInit() {
    this.display();
  }


  display() {
    let _self = this;
    _self.application = null;
    _self.session = null;
    _self.request = null;

    let url = this.locator.endpoints.APPINFO;

    this.locator.http.get(url, function (res, error) {

      if (error) {
        console.log(error);
        return;
      }

      _self.form.error = !res.success;
      _self.form.message = res.result.message;

      //if (res.success) {
      _self.application = res.result.application;
      _self.session = res.result.session;
      _self.request = res.result.request;


      _self.application = Object.keys(_self.application).map((key) => [key, JSON.stringify(_self.application[key])]);
      _self.session = Object.keys(_self.session).map((key) => [key, JSON.stringify(_self.session[key])]);
      _self.request = Object.keys(_self.request).map((key) => [key, JSON.stringify(_self.request[key])]);

      console.log("123", _self.application);
      //}
    });

  }

  getConfFile(fname) {
    let url = this.locator.endpoints.APPINFO + "/config?name=" + fname;
    url = this.locator.http.urlRewrite(url) ;
    return url;
  }


  getLocalStorageKeys() {
    let  keys = Object.keys(localStorage);
    return keys;
  }

  
  getLocalStorageValue(key) {
    return localStorage.getItem(key);
  }


}