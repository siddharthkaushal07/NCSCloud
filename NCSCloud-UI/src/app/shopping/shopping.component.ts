import { Component, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventEmitter } from 'protractor';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { HttpServiceService } from '../common/service/http-service.service';
import { ServiceLocatorService } from '../common/service/service-locator.service';


@Component({
  selector: 'app-shopping',
  templateUrl: './shopping.component.html',
  styleUrls: ['./shopping.component.css']
})
export class ShoppingComponent extends BaseListCtl {
 
  fileToUpload: any;

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, public http: HttpServiceService) {
    super(locator.endpoints.SHOPPING, locator, route);
  }

  priceForm = {
    list: { price: null },
    ppid: null,
    productName:null,
    image_id:null
  }

  ngOnInit() {
    super.ngOnInit();

  }

  onFileSelect(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);

  }

 
orderNo
  

  // addOrderNo(){
  //   let _self=this;
  //   let url = "http://localhost:9102/NCSCloud/Order/orderNo"

  //   this.locator.http.get(url, function (res, error) {
  //     if (error) {
  //       return;
  //     } 
     
  //     if (res.success) {
 
  //     _self.orderNo = res.result.data[0]
  //       console.log("max order no-------------"+ _self.orderNo );
  //     }
  //   });
  // }

  addCart() {
    // this.priceForm.ppid = id;
    // this.priceForm.productName=productName;
    // this.priceForm.image_id=image_id;
    //this.addOrderNo();
    let _self=this;
    let url = "http://localhost:9102/NCSCloud/Order/orderNo"

    this.locator.http.get(url, function (res, error) {
      if (error) {
        return;
      } 
     
      if (res.success) {
 
      _self.orderNo = res.result.data[0]
        console.log("max order no-------------"+ _self.orderNo );
        _self.addtest(_self.orderNo)
      }
    });
  
   
    // _self.searchingProduct(_self.form.data.id);
  }
  addtest(no) {
   let _self=this
    console.log("in addcart of shopping...................................."+no)
    for( let i =0; i < this.form.list.length; i++){
      console.log("ppid=" + this.priceForm.ppid + "------" +this.priceForm.productName+"-----"+this.priceForm.image_id);
    
    let cartForm = {

      productName: this.form.list[i].productName,
      image_id: this.form.list[i].image_id,
      price:this.form.list[i].price,
      gst:this.form.list[i].gst,
      model_no:this.form.list[i].model_no,
      quantity:this.form.list[i].quantity,
      order_no:no+1
    }
    let url = _self.locator.endpoints.ORDER;
    _self.http.post(url + '/save', cartForm, function (res, error) {
      if (error) {
        return;
      }
      _self.form.data = res.result.data;
      
      console.log("addcart"+ _self.form.data.id);

      _self.form.message = "Data is saved";

    })
    
    console.log(i )
    console.log(this.form.list.length)

//# delete after product add into ordertable----------------

    if(i ===  this.form.list.length-1){
      console.log("ghjd"+i)
      for( let j =0; j < this.form.list.length; j++){
        this.deleteShopping(this.form.list[j].id);
      }
    }
    }

    
  }

  CatalogForm = {
    data: {

    },
    list: []
  }
  populateForm(form, data) {
    
    form.id = data.id;
    form.productName = data.productName;
    form.price=data.price;
    form.model_no=data.model_no;
    form.gst=data.gst;
    form.quantity = data.quantity;
    form.image_id = data.image_id;

    }
 
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


//for total
length;
    search(){
  
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
          _self.form.list = res.result.data;
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
          _self.getPrice();
          console.log("List Size", _self.form.list.length);
        } 
      });
      
    }

    totalPrice;
    tax;
    shippingCharges;
    grandTotal;

    
    

  
    getPrice() {
      let _self=this;
      console.log(this.form.list)
      _self.totalPrice = 0;
     
      _self.shippingCharges = 40;
      _self.grandTotal=0;
      for (let i = 0; i <= _self.form.list.length - 1; i++) {
        let q = _self.form.list[i]['quantity'];
        console.log("quantity inside getprice==== "+_self.form.list[i]['quantity'])
  
        if (q > 0) {
          _self.totalPrice = _self.form.list[i]['price'] * q + _self.totalPrice
        
          _self.grandTotal = _self.totalPrice  + _self.shippingCharges;
        }
  
      }
      console.log("hhhhhhhhhhhhh"+this.grandTotal)
  
    }



    // totalPrice;
    // getPrice(){
    //   console.log(this.form.list)
    //   this.totalPrice = 0;
    //   for(let i=0; i<=this.form.list.length-1;i++)
    //   {
    //     let q= this.form.list[i]['quantity'];
        
       
    //    if(q>0){
    //   this.totalPrice=this.form.list[i]['price']*q+this.totalPrice
    //    }
        
         
    //   }
    // console.log(this.totalPrice)
    
    // }



saveQuantity(q,pn,img,price,gst,mn,id){
  this.form.data["id"]=id;
  this.form.data["productName"]=pn;
  this.form.data["quantity"]=q;
  this.form.data["image_id"]=img;
  this.form.data["price"]=price;
  this.form.data["gst"]=gst;
  this.form.data["model_no"]=mn;
  console.log("quantity=== "+  this.form.data["quantity"])
  this.getPrice();
 super.save();
}

}