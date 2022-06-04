import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';

import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent extends BaseListCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    
    super(locator.endpoints.ORDER, locator, route);
 

  }
ngOnInit() {
  super.ngOnInit();
  
  //super.ngOnInit();
  // this.orderPreload();
   console.log("get order data================")
   this.orderSearch();
   //this.orderItemSearch();
  console.log("get order data================")
   // this.oService.getOrder();
   this.searchItem('','','');
   
}
 

populateForm(form, data) {
    
  form.id = data.id;
  form.orderId = data.orderId;
  form.total_price = data.total_price;
  form.no_of_items = data.price;
  form.quantity=data.quantity;
  form.discount = data.discount;
  form.description=data.description;
  form.shipping_Address_Id=data.shipping_Address_Id;
  form.billing_Address_Id=data.billing_Address_Id;
  
  
} 



getItem(id){
  this.orderItemForm.searchParams['order_id']=id;
}

public orderItemForm = {

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

orderList ;

searchItem(id:any,sid,bid) {
  console.log(sid+"============================="+bid)
  this.orderList = new Array();

  console.log("OrderItemCtl search run...")

  var _self = this;

  _self.orderItemForm.searchParams['order_id']=id

  _self.orderItemForm.searchError = false;
  _self.orderItemForm.searchMessage = "";
  console.log("Search Form//////////////////////////// ", _self.orderItemForm.searchParams);
  //_self.form.searchParams=id;
  let url = _self.locator.endpoints.ORDERITEM + "/search/" + _self.orderItemForm.pageNo;

  this.locator.http.post(url, _self.orderItemForm.searchParams, function (res, error) {

    if (error) {
      return;
    }

    _self.orderItemForm.searchError = !res.success;
    _self.orderItemForm.searchMessage = res.result["message"];

    if (res.success) {
      console.log("inside baselistctl success------------->")
      _self.orderItemForm.list = res.result.data;
      var temp = _self.orderItemForm.list[0].order_id;
      var j = 0;
      _self.orderList[j] = new Array()
      for (var i = 0; i < _self.orderItemForm.list.length; i++) {
        var value:any = _self.orderItemForm.list[i];
        if (temp == value.order_id) {
          _self.orderList[j].push(_self.orderItemForm.list[i]);
        } else {
            j++;
          _self.orderList[j] = new Array()
          _self.orderList[j].push(_self.orderItemForm.list[i]);
          temp = _self.orderItemForm.list[i].order_id;
         
        }
      }

      console.log("ordrItemList-------------------= "+_self.orderList)
      // for (var i = 0; i < _self.orderList.length; i++) {
      //   for (var j = 0; j <= _self.orderList[i].length; j++) {
      //     console.log(_self.orderList[i][j].order_id)
      //   }
      //   console.log("break");
      // }



      console.log("llllllllllllllllllllllllllll" + _self.orderItemForm.list);

      _self.orderItemForm.pageCount = res.result.pageCount;
      _self.orderItemForm.pageSize = res.result.pageSize;
      _self.orderItemForm.pageNoList = [];
      //Build page number list
      for (let i = 0; i < _self.orderItemForm.pageCount; i++) {
        _self.orderItemForm.pageNoList.push(i);
      }

      //Check empty list
      if (_self.orderItemForm.list.length == 0) {
        _self.orderItemForm.message = "No record found";
        _self.orderItemForm.error = true;
      }

      console.log("List Size------------", _self.orderItemForm.list.length);

    }
  
  });
  this.orderSearch();
  this.getShipping(sid);
  this.getBilling(bid);
}


public shippingForm = {

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

getShipping(sId){
  let _self=this;
  let url = _self.locator.endpoints.SHIPPING + "/get/" + sId;

  this.locator.http.get(url, function (res, error) {
    
    if (error) {
      return;
    }
    _self.shippingForm.error = !res.success;
    _self.shippingForm.message = res.result.message;

    if (res.success) {
      console.log("inside get basectl-----");
    //  console.log( res.result.data["name"])
      _self.shippingForm.data.id = sId;
      _self.shippingForm.data=res.result.data
      console.log("shipping data----========= in order >>>>>>>>>  "+_self.shippingForm.data['name'])
 
      _self.populateForm(_self.shippingForm.data, res.result.data);
    }
  });
  
}

getBilling(bId){
  let _self=this;
  let url = _self.locator.endpoints.BILLING + "/get/" + bId;

  this.locator.http.get(url, function (res, error) {
    
    if (error) {
      return;
    }
    _self.form.error = !res.success;
    _self.form.message = res.result.message;

    if (res.success) {
      console.log("inside get basectl-----");
    //  console.log( res.result.data["name"])
      _self.form.data.id = bId;
      _self.form.data=res.result.data
      console.log("billing data----========= in order >>>>>>>>>  "+_self.form.data)
     // _self.populateForm(_self.form.data, res.result.data);
    }
  });

}


// orderItemSearch(){


//   console.log("BaseCtl search run...")
//     var _self = this;

//     _self.orderForm.searchError = false;
//     _self.orderForm.searchMessage = "";    

//     console.log("Search Form", _self.orderForm.searchParams);

//     let url = "http://localhost:9102/NCSCloud/Order/search/" + _self.orderForm.pageNo;

//     this.locator.http.post(url, _self.orderForm.searchParams, function (res, error) {

//       if (error) {
//         return;
//       }

//       _self.orderForm.searchError = !res.success;
//       _self.orderForm.searchMessage = res.result["message"];

//       if (res.success) {
//         console.log("inside baselistctl success------------->")
//         _self.orderForm.list = res.result.data;
//         console.log( "llllllllllllllllllllllllllll"+_self.orderForm.list );
        
//         _self.orderForm.pageCount = res.result.pageCount;
//         _self.orderForm.pageSize = res.result.pageSize;
//         _self.orderForm.pageNoList = [];
//         //Build page number list
//         for (let i = 0; i < _self.orderForm.pageCount; i++) {
//           _self.orderForm.pageNoList.push(i);
//         }

//         //Check empty list
//         if (_self.form.list.length == 0) {
//           _self.orderForm.message = "No record found";
//           _self.orderForm.error = true;
//         }
      
//         console.log("List Size order===========------------", _self.orderForm.list.length);
//         console.log("product name--------======== "+_self.orderForm.list[0]['order_id'])
      
//       } 
//     });
// }

public orderForm = {
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

orderFormList;


orderSearch() {
  console.log("BaseCtl search run...")
  var _self = this;

  _self.orderItemForm.searchError = false;
  _self.orderItemForm.searchMessage = "";    

  console.log("Search Form", _self.orderItemForm.searchParams);

  let url = _self.locator.endpoints.ORDER + "/search/" +_self.orderItemForm.pageNo;

  this.locator.http.post(url, _self.orderItemForm.searchParams, function (res, error) {

    if (error) {
      return;
    }

    _self.orderItemForm.searchError = !res.success;
    _self.orderItemForm.searchMessage = res.result["message"];

    if (res.success) {
      console.log("inside baselistctl success------------->")
      _self.orderFormList = res.result.data;
      console.log("inside order form List //////////////"+_self.orderFormList);
      //console.log( "llllllllllllllllllllllllllll"+_self.form.list );
      
      _self.orderItemForm.pageCount = res.result.pageCount;
      _self.orderItemForm.pageSize = res.result.pageSize;
      _self.orderItemForm.pageNoList = [];
      //Build page number list
      for (let i = 0; i < _self.orderItemForm.pageCount; i++) {
        _self.orderItemForm.pageNoList.push(i);
      }

      //Check empty list
      if (_self.orderItemForm.list.length == 0) {
        _self.orderItemForm.message = "No record found";
        _self.orderItemForm.error = true;
      }
    
      console.log("List Size in order-=========------------", _self.orderItemForm.list.length);
    
    } 
  });
}

    }

