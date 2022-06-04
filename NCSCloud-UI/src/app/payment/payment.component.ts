import { DatePipe, LocationStrategy } from '@angular/common';
import { ChangeDetectorRef, Component, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { HttpServiceService } from '../common/service/http-service.service';
import { ServiceLocatorService } from '../common/service/service-locator.service';
import { UtilserviceService } from '../utilservice.service';
declare let Razorpay:any;
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent extends BaseListCtl {
  // @Input() testPrice:number;
  
  constructor(private datepipe:DatePipe,private location: LocationStrategy,public locator: ServiceLocatorService, public route: ActivatedRoute, public http: HttpServiceService,private razorpayService: UtilserviceService, private cd:  ChangeDetectorRef, ) {
    super(locator.endpoints.SHOPPING, locator, route);

    history.pushState(null, null, window.location.href);  
this.location.onPopState(() => {
  history.pushState(null, null, window.location.href);

});


}


date:Date;
  myFunction(){
    this.date=new Date();
    let latest_date =this.datepipe.transform(this.date, 'yyyy-MM-dd');
    this.date.setDate( this.date.getDate() + 7 );

   }

  name = 'Angular';
  response;
  razorpayResponse;
  showModal = false;

  ngOnInit() {
    
    super.ngOnInit();
    this.myFunction();
    console.log("paymode===== "+this.selectedPayMode)
    this.mylist();
   this.search();
   this.searchLenth();
  // console.log("testPrice------------------------"+this.testPrice);
   
    this.razorpayService
      .lazyLoadLibrary('https://checkout.razorpay.com/v1/checkout.js')
      .subscribe();
  }
amount:number=0
  RAZORPAY_OPTIONS = {
    "key": "rzp_test_RKrpNWpwbff04f",
    "amount":"",
    "name": "Test",
    "order_id": "",
    "description": "Load Wallet",
    "image": "../../assets/img/razorpay.png",
    "prefill": {
      "name": "",
      "email": "testing123@gmail.com",
      "contact": "",
      "method": ""
    },
    "modal": {},
    "theme": {
      "color": "#0096C5"
    }
  };
  off:boolean=true;
  offButton(){
    this.off=false;
    console.log("offButton------ "+this.off)
   return this.off;
//    this.offButton2(this.off);
  }

  offButton2(){
    console.log("offButton------ "+this.off)
 return this.off=true;  
}



 public proceed() {
    this.RAZORPAY_OPTIONS.amount = this.grandTotal + "00";

    // binding this object to both success and dismiss handler
    this.RAZORPAY_OPTIONS['handler'] = this.razorPaySuccessHandler.bind(this);

    // this.showPopup();

    let razorpay = new Razorpay(this.RAZORPAY_OPTIONS)
    razorpay.open();
  }

  public razorPaySuccessHandler(response) {
    console.log(response);
    console.log(response.razorpay_payment_id)
    if (response.razorpay_payment_id == response.razorpay_payment_id ) {
      this.addCart();
     // location.href = "http://localhost:4200/#/order";
      
    } 
    this.razorpayResponse = `Razorpay Response`;
    this.showModal = true;
    this.cd.detectChanges()
    document.getElementById('razorpay-response').style.display = 'block';
     
    
     
  }

  onPayment(){
    
    return false;
  }



  //#------------shopping data here----------------------------------------------
// search(): void {
//     console.log("55555555555555555"+this.form.list.length);
// }

flexRadioDefault=1;

paymentmode(a){

this.flexRadioDefault=a.target.value;
console.log("pm value==============="+this.flexRadioDefault);
}

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
    _self.getPrice();
    } 
  });
  //console.log("list lenth inside order review"+_self.form.list.length);
//this.redirect(_self.form.list.length);
}
mypage="/catalogue";
lenth;

redirect(l){
  let lenth=l
  console.log("list lenth inside review 7414714777777777============"+ lenth);
if(lenth==0){
  console.log("4444444444444444444444444444444444444")
  this.forward(this.mypage);
}

}


  orderNo
  addCart() {
    // this.priceForm.ppid = id;
    // this.priceForm.productName=productName;
    // this.priceForm.image_id=image_id;
    //this.addOrderNo();
    let _self=this;
    let url = "http://localhost:9102/NCSCloud/OrderItem/orderNo"

    this.locator.http.get(url, function (res, error) {
      if (error) {
        return;
      } 
     
      if (res.success) {
 
      _self.orderNo = res.result.data[0]
        console.log("max order no in payment component=========-------------"+ _self.orderNo );
        _self.addOrder(_self.orderNo)
        location.href = "http://localhost:4200/#/success-order"
      }
    });
  
  }

  mylist(){
    let _self=this;
    console.log("list lenth in payment==========="+ this.form.list.length)
  }

  


  priceForm = {
    list: { price: null },
    ppid: null,
    productName:null,
    image_id:null
  }

  // cartForm={
   
  // }
  paymentModeStatus:string;
  addOrder(no) {
    let _self=this
    let total:number=0;
    let total_quantity=0;
    let oId:number=no+1;

     console.log("in add order of payment orderNo--------...................................."+no)
     
     console.log("list size in payment=====************ "+this.form.list.length);
    //  total_quantity=this.form.list[i]['quantity']+total_quantity;
    

     for( let i =0; i <  this.form.list.length; i++){
     var value  = this.form.list[i];
     total+=value.price*value.quantity; 
    //  total=this.form.list[i]['price']+total;
       total_quantity=this.form.list[i]['quantity']+total_quantity;
      
     }

     console.log("--------------------->>>>>>>>>>>>> "+total);
     console.log("----------total_quantity----------->>>>>>>>>>>>> "+total_quantity);
     for( let i =0; i < this.form.list.length; i++){
         //   console.log("ppid=" + this.priceForm.ppid + "------" +this.priceForm.productName+"-----"+this.priceForm.image_id);
   //  console.log("price in addcart===========********* "+this.form.list[i].price)
    // console.log(this.form.list[i].price+this.form.list[i].price)
   // console.log("total inside cart****************** "+total);
     
   let cartForm = {
      
       productName: this.form.list[i].productName,
       image_id: this.form.list[i].image_id,
       price:this.form.list[i].price,
       gst:this.form.list[i].gst,
       modelNo:this.form.list[i].model_no,
       quantity:this.form.list[i].quantity,
       order_id:no+1,
       payment_mode:this.selectedPayMode,
       
       billing_Address_Id: localStorage.getItem("bid"),
       shipping_Address_Id: localStorage.getItem("sid")

     }
     console.log("product name in side payment-------"+ this.form.list[i].productName);

    
    // console.log("total price of product====----->"+totalPrice);
     let url = _self.locator.endpoints.ORDERITEM;
     _self.http.post(url+'/placeOrder', cartForm, function (res, error) {
       if (error) {
         return;
       }
       if(res.success){
       _self.form.data = res.result.data;
       
       console.log("addcart"+ _self.form.data.id);
 
       _self.form.message = "Data is saved";
       localStorage.removeItem("sid");
       localStorage.removeItem("bid");
       }
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
//add order table on the basis of orderId
total=total+40;
console.log("total price after lopp----------------"+total);
console.log("total quantity after loop----------------"+total_quantity);
let discount:number=10;

let tp=total/discount;
tp=total-tp;

console.log("total price after discount----------------"+tp);
let order={
  total_price:tp,
  quantity:total_quantity,
  description:"Thankyou for shopping from Rays Ecom",
  discount:discount,
  order_id:oId

}
//calling order to Add OrderData
let url = _self.locator.endpoints.ORDER;
     _self.http.post(url+'/save', order, function (res, error) {
       if (error) {
         return;
       }
       if(res.success){
       _self.form.data = res.result.data;
       
       console.log("addcart"+ _self.form.data.id);
 
       _self.form.message = "Data is saved";
       
       }
     })
     
   }

//add order table on the basis of orderId
addOrderData(){
  // let _self=this;
  // let url = _self.locator.endpoints.ORDERITEM;
  //    _self.http.post(url+'/placeOrder', cartForm, function (res, error) {
  //      if (error) {
  //        return;
  //      }
  //      if(res.success){
  //      _self.form.data = res.result.data;
       
  //      console.log("addcart"+ _self.form.data.id);
 
  //      _self.form.message = "Data is saved";
  //      localStorage.removeItem("sid");
  //      localStorage.removeItem("bid");
  //      }
  //    })
}


   //delete product

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
  


  //  deleteShopping(id, callback?) {

  //   var _self = this;
  //   _self.form.error = false;
  //   _self.form.message = "";   

    

  //   this.locator.http.get(_self.api.delete + "/" + id, function (res, error) {

  //     if (error) {
  //       return;
  //     }

  //     _self.form.error = !res.success;
  //     _self.form.message = res.result.message;

  //     if (res.success) {
  //       _self.form.message = "Data is deleted";
  //       if (callback) {
  //         callback();
  //       }
        
  //     }
      
  //   });
  // }  
  totalPrice
  shippingCharges;
  grandTotal;

  getPrice2(){
   return this.grandTotal;
  }

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
    // this.getPrice2(_self.grandTotal);
    console.log(this.totalPrice)

  }


  deleteAdd(bid){
    console.log("");
    let _self=this;
    let url =_self.locator.endpoints.BILLING
//    "http://localhost:9102/NCSCloud/Billing/delete/"+bid; 

    this.locator.http.get(url +"/delete/"+bid, function (res, error) {
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
});

} 

testchange()
{
  console.log("multiple on-change calling================")
}
/////////////////////////////////////////////////////////

  selectedPayMode: string= 'COD';
  days : any=[
    "1234",
    "1111",
  ] ;

   changePayMode(event:any){
    this.selectedPayMode=event.target.value;
   }

  //show current date
 
}

