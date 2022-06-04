import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-order-item',
  templateUrl: './order-item.component.html',
  styleUrls: ['./order-item.component.css']
})
export class OrderItemComponent extends BaseListCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    
    super(locator.endpoints.ORDERITEM, locator, route);
 

  }
ngOnInit() {
  super.ngOnInit();
 // this.orderPreload();
  console.log("get order data================")
  this.orderSearch();
  console.log("------------------------");
  
   // this.oService.getOrder();
}


populateForm(form, data) {
    
  form.id = data.id;
  form.productName = data.productName;
  form.modelNo = data.modelNo;
  form.gst = data.gst;
  form.payment_mode = data.payment_mode;
  form.billing_Address_Id=data.billing_Address_Id;
  form.shipping_Address_Id=data.shipping_Address_Id;
  form.image_id=data.image_id;
  form.order_id=data.order_id;
  form.price=data.price;
  form.total_price=data.total_price;
} 

// orderPreload() {
//   var _self = this;

//   _self.form.error = false;
//   _self.form.message = "";
// let url=_self.locator.endpoints.ORDERITEM;
//   this.locator.http.get(url+"/preload", (res, error) => {
    
//     if (error) {
//       return;
//     }

//     _self.form.error = !res.success;
//     _self.form.message = res.result.message;

//     if (res.success) {
//       _self.form.preload = res.result;
//     } 

//     console.log('FORM', _self.form);
//   });
// }



placeOrder(callback){
  console.log("inside placeOrder method--------------------------")
    var _self = this;

    _self.form.error = false;
    _self.form.message = "";
    _self.form.inputerror = {};
    let url= _self.locator.endpoints.ORDERITEM
    console.log("quantity--------------=== ******** "+this.form.data['quantity'])
    this.locator.http.post(url+"/placeOrder", this.form.data, function (res, error) {

      if (error) {
        return;
      }

      _self.form.error = !res.success;
      _self.form.message = res.result.message;

      if (res.success) {
        _self.form.message = "Data is saved";
        if (callback) {
          callback();
        }
      } else {
        _self.form.inputerror = res.result.inputerror;
      }
      console.log('FORM', _self.form);
    });
  }


  ///////////search 

  
  orderList ;

  search() {
    this.orderList = new Array();
    console.log("OrderItemCtl search run...")
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
        console.log("inside baselistctl success------------->")
        _self.form.list = res.result.data;
        var temp = _self.form.list[0].order_id;
        var j = 0;
        _self.orderList[j] = new Array()
        for (var i = 0; i < _self.form.list.length; i++) {
          var value:any = _self.form.list[i];
          if (temp == value.order_id) {
            _self.orderList[j].push(_self.form.list[i]);
          } else {
              j++;
            _self.orderList[j] = new Array()
            _self.orderList[j].push(_self.form.list[i]);
            temp = _self.form.list[i].order_id;
           
          }
        }

        console.log("ordrItemList-------------------= "+_self.orderList)
        // for (var i = 0; i < _self.orderList.length; i++) {
        //   for (var j = 0; j <= _self.orderList[i].length; j++) {
        //     console.log(_self.orderList[i][j].order_id)
        //   }
        //   console.log("break");
        // }



        console.log("llllllllllllllllllllllllllll" + _self.form.list);

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
    this.orderSearch();
  }
//find by Order Id=======

//  getByOrderId(){
//  let searchParams: {"8"};
//   console.log("orderitem component---- search run...")
//   var _self = this;

//   _self.form.searchError = false;
//   _self.form.searchMessage = "";    

//   console.log("Search Form", _self.form.searchParams);

//   let url = _self.locator.endpoints.ORDER+  "/search/" + _self.form.pageNo;

//   this.locator.http.post(url, searchParams, function (res, error) {

//     if (error) {
//       return;
//     }

//     _self.form.searchError = !res.success;
//     _self.form.searchMessage = res.result["message"];

//     if (res.success) {
//       console.log("inside baselistctl success------------->")
//       _self.form.list = res.result.data;
//       console.log( "llllllllllllllllllllllllllll"+_self.form.list );
      
//       _self.form.pageCount = res.result.pageCount;
//       _self.form.pageSize = res.result.pageSize;
//       _self.form.pageNoList = [];
//       //Build page number list
//       for (let i = 0; i < _self.form.pageCount; i++) {
//         _self.form.pageNoList.push(i);
//       }

//       //Check empty list
//       if (_self.form.list.length == 0) {
//         _self.form.message = "No record found";
//         _self.form.error = true;
//       }
    
//       console.log("List Size------------", _self.form.list.length);
    
//     } 
//   });
// }


////order list

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

orderItemSearch(){


  console.log("BaseCtl search run...")
    var _self = this;

    _self.orderForm.searchError = false;
    _self.orderForm.searchMessage = "";    

    console.log("Search Form", _self.orderForm.searchParams);

    let url = "http://localhost:9102/NCSCloud/Order/search/" + _self.orderForm.pageNo;

    this.locator.http.post(url, _self.orderForm.searchParams, function (res, error) {

      if (error) {
        return;
      }

      _self.orderForm.searchError = !res.success;
      _self.orderForm.searchMessage = res.result["message"];

      if (res.success) {
        console.log("inside baselistctl success------------->")
        _self.orderForm.list = res.result.data;
        console.log( "llllllllllllllllllllllllllll"+_self.orderForm.list );
        
        _self.orderForm.pageCount = res.result.pageCount;
        _self.orderForm.pageSize = res.result.pageSize;
        _self.orderForm.pageNoList = [];
        //Build page number list
        for (let i = 0; i < _self.orderForm.pageCount; i++) {
          _self.orderForm.pageNoList.push(i);
        }

        //Check empty list
        if (_self.form.list.length == 0) {
          _self.orderForm.message = "No record found";
          _self.orderForm.error = true;
        }
      
        console.log("List Size------------", _self.orderForm.list.length);
      
      } 
    });
}



orderFormList;
///orderTable List-----------

orderSearch() {
  console.log("BaseCtl search run...")
  var _self = this;

  _self.form.searchError = false;
  _self.form.searchMessage = "";    

  console.log("Search Form", _self.form.searchParams);

  let url = _self.locator.endpoints.ORDER + "/search/" +_self.form.pageNo;

  this.locator.http.post(url, _self.form.searchParams, function (res, error) {

    if (error) {
      return;
    }

    _self.form.searchError = !res.success;
    _self.form.searchMessage = res.result["message"];

    if (res.success) {
      console.log("inside baselistctl success------------->")
      _self.orderFormList = res.result.data;
      console.log("inside order form List //////////////"+_self.orderFormList);
      //console.log( "llllllllllllllllllllllllllll"+_self.form.list );
      
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
