import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalConstants } from '../GlobalConstants ';
import { DataValidatorService } from './data-validator.service';
import { EventService } from './event.service';
import { HttpServiceService } from './http-service.service';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  constructor(private router: Router, public http:HttpServiceService) {
   }
   public form = {
    error: false, //error 
    message: null, //error or success message
    preload: null, // preload data
    inputerror: {}, // form input error messages
    data: { id: null }, //form data
    //Attributes for list page 
    searchError: false, //error 
    searchParams: {}, //search form
    searchMessage: null, //search result message
    
    list:[], // search list 
    pageNo: 0, //current age number of the list page 
    pageCount: 0, //Total record count of search result
    pageSize: 0, //Page size of the list 
    pageNoList: [] //Dropdown list of page numbers 
  };



   getOrder(){
     let _self=this;
     let url="http://localhost:9102/NCSCloud/Order/search"
    
    _self.http.post(url, _self.form.searchParams, function (res, error) {

      if (error) {
        return;
      }

      _self.form.searchError = !res.success;
      _self.form.searchMessage = res.result["message"];

      if (res.success) {
        console.log("inside baselistctl success------------->")
        _self.form.list = res.result.data;
        console.log("iddddddddddddddd=---------->>>> "+ res.result.data)
        
        
        for(let i=0;i<_self.form.list.length;i++){
          
          console.log( "price list in order service---- "+_self.form.list[i]['order_no'] );
        }

        _self.form.pageCount = res.result.pageCount;
        _self.form.pageSize = res.result.pageSize;
        _self.form.pageNoList = [];
        //Build page number list
        for (let i = 0; i < _self.form.pageCount; i++) {
          _self.form.pageNoList.push(i);
        }

        //Check empty list
        if (_self.form.list.length == 0) {
          _self.form.message = "No record found";
          _self.form.error = true;
        }
      
        console.log("List Size------------", _self.form.list.length);
      
      } 
    });
  }  




  
}
