import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { HttpServiceService } from '../common/service/http-service.service';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-review-order',
  templateUrl: './review-order.component.html',
  styleUrls: ['./review-order.component.css']
})
export class ReviewOrderComponent extends BaseListCtl {

  constructor(private location: LocationStrategy,public locator: ServiceLocatorService, public route: ActivatedRoute, public http: HttpServiceService) {
    super(locator.endpoints.SHOPPING, locator, route);
    history.pushState(null, null, window.location.href);  
    this.location.onPopState(() => {
      history.pushState(null, null, window.location.href);
    });  
  }

  ngOnInit() {
super.ngOnInit();
//this.redirect();
this.bSearch();
this.searchLenth();
// this.getMaxbid();
}

msg="Your Order has been successfully placed!!"

searchLenth() {
  console.log("BaseCtl search run...")
  var _self = this;

  _self.form.searchError = false;
  _self.form.searchMessage = "";    

  console.log("Search Form", _self.form.searchParams);

  let url = _self.api.search + "/" + _self.form.pageNo;

  this.locator.http.post(url, _self.form.searchParams, function (res, error) {

    if (error) {
      return;
    }

    _self.form.searchError = !res.success;
    _self.form.searchMessage = res.result["message"];

    if (res.success) {
      console.log("inside payment lenthseasrch success------------->")
      _self.form.list = res.result.data;
     // console.log( "llllllllllllllllllllllllllll"+_self.form.list );
      
     

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
        _self.redirect(_self.form.list.length)
      }
    
      console.log("List Size------------", _self.form.list.length);
     _self.lenth=_self.form.list.length;
    // _self.myLenth(_self.form.list.length);
    //  console.log("list lenth inside basectl"+_self.lenth);
    
    } 
  });
  //console.log("list lenth inside order review"+_self.form.list.length);
//this.redirect(_self.form.list.length);
}



mypage="/catalogue";
lenth




redirect(l){
  let lenth=l
  console.log("list lenth inside review 7414714777777777============"+ lenth);
if(lenth==0){
  console.log("4444444444444444444444444444444444444")
  this.forward(this.mypage);
}

}

deleteProduct(){
  for( let i =0; i < this.form.list.length; i++){
    if(i ===  this.form.list.length-1){
      console.log("ghjd"+i)
      for( let j =0; j < this.form.list.length; j++){
        this.deleteShopping(this.form.list[j].id);
      }
    }
   
    }

}
  // for( let i =0; i < this.form.list.length; i++){

   
  //   }



     //delete product
   deleteShopping(id, callback?) {

    var _self = this;
    _self.form.error = false;
    _self.form.message = "";   

    

    this.locator.http.get(_self.api.delete + "/" + id, function (res, error) {

      if (error) {
        return;
      }

      _self.form.error = !res.success;
      _self.form.message = res.result.message;

      if (res.success) {
        _self.form.message = "Data is deleted";
        if (callback) {
          callback();
        }
        
      }
      
    });
  }  

  deleteAdd(bid){
    console.log("");
    let _self=this;
    let url ="http://localhost:9102/NCSCloud/Billing/delete/"+bid; 

    this.locator.http.get(url, function (res, error) {
      console.log("delete url inside review--------- "+url);
      
      if (error) {
        return;
      } 
     
      if (res.success) {
        console.log("Address delete Successfully-------------")

      }
    });
  }

  maxBid
  getMaxbid(){
    console.log("");
    let _self=this;
    let url ="http://localhost:9102/NCSCloud/Billing/maxBid"; 

    this.locator.http.get(url, function (res, error) {
      console.log("url inside review--------- "+url);
      
      if (error) {
        return;
      } 
     
      if (res.success) {
 
      _self.maxBid = res.result.data[0]

  }
  console.log("max bid----------->>>>>>> "+_self.maxBid)
_self.deleteAdd(_self.maxBid);
localStorage.removeItem("sid");
localStorage.removeItem("bid");
});

} 




///// billing searching for list length and save to localstorage--------------

public bForm = {
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


bSearch() {
  console.log("billing search run...-----------")
  var _self = this;

  _self.bForm.searchError = false;
  _self.bForm.searchMessage = "";

  console.log("Search Form", _self.bForm.searchParams);

  let url ="http://localhost:9102/NCSCloud/Billing/search/" + _self.bForm.pageNo;

  this.locator.http.post(url, _self.bForm.searchParams, function (res, error) {

    if (error) {
      return;
    }

    _self.bForm.searchError = !res.success;
    _self.bForm.searchMessage = res.result["message"];

    if (res.success) {
      console.log("inside baselistctl success------------->")
      _self.bForm.list = res.result.data;


      _self.bForm.pageCount = res.result.pageCount;
      _self.bForm.pageSize = res.result.pageSize;
      _self.bForm.pageNoList = [];
      //Build page number list
      for (let i = 0; i < _self.bForm.pageCount; i++) {
        _self.bForm.pageNoList.push(i);
      }

      //Check empty list
      if (_self.bForm.list.length == 0) {
        _self.bForm.message = "No record found";
        _self.bForm.error = true;
      }

      console.log("List Size------------ for billing ", _self.bForm.list.length);
        let bid:number=_self.bForm.list.length-1
        console.log("max bid=== "+ res.result.data[bid]["id"] )
        
        if(localStorage.getItem("bid")==null && localStorage.getItem("bid")==undefined){
        localStorage.setItem("bid",res.result.data[bid]["id"]);
        }
        console.log("billing id from local storage===+ "+ localStorage.getItem("bid"));
    }
  });
}


}
