import { formatCurrency } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-productview',
  templateUrl: './productview.component.html',
  styleUrls: ['./productview.component.css']
})
export class ProductviewComponent implements OnInit {

  constructor(public locator: ServiceLocatorService,public route: ActivatedRoute) {
let _self = this;

    locator.getPathVariable(route, function (params) {
     let id = params["id"];
      if(id){
        _self.getProduct(id);
      }
    })
   }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  form ={
    data:{id:null},
    success : false
  }
  getProduct(id: any) {
    let _self = this;
    let url = this.locator.endpoints.PRODUCT;
    this.locator.http.get( url+ "/get/" + id, function (res, error) {
  
      if (error) {
        return;
      }
      if (res.success) {
        _self.form.data.id = id;
       _self.form.data = res.result.data
        _self.getPrice(_self.form.data['name'])
      } 
    });


  }
priceform={
  data:{}
}
  getPrice(name: any) {
    let _self =this;
    let newForm ={
      name : name,
      data:{}
    }
    let url=this.locator.endpoints.PRODUCTPRICE
    this.locator.http.post(url+"/search/0",newForm, function (res, error) {

      if (error) {
        return;
      }
_self.priceform.data = res.result.data[0];
console.log(_self.priceform.data[0].mrp)
   console.log( _self.form.data['mrp'])
  });
}


  success = false;
priceForm={
  ppid:null,
  name:null,
  image_id:null
}
  addCart(id, name, image_id) {
    this.priceForm.ppid = id;
    this.priceForm.name=name;
    this.priceForm.image_id=image_id;
    console.log("ppid=" + this.priceForm.ppid + "------" +this.priceForm.name+"-----"+this.priceForm.image_id);
    let _self = this;
    let cartForm = {
      productName: name,
      image_id: image_id,
    }
    let url = _self.locator.endpoints.SHOPPING;
    _self.locator.http.post(url + '/addItem', cartForm, function (res, error) {
      if (error) {
        return;
      }
      _self.success = true

      console.log("addcart"+ _self.form.data.id);

      

    })
    // _self.searchingProduct(_self.form.data.id);
  }
}

