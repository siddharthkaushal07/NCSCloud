import { DatePipe } from '@angular/common';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { HttpServiceService } from '../common/service/http-service.service';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-catalogue',
  templateUrl: './catalogue.component.html',
  styleUrls: ['./catalogue.component.css']
})
export class CatalogueComponent extends BaseListCtl {
  fileToUpload: File;
  
  constructor(public datepipe: DatePipe,public locator: ServiceLocatorService,public router:Router, public route: ActivatedRoute, public http: HttpServiceService) {
    super(locator.endpoints.PRODUCT, locator, route);

  }

  

  priceForm = {
    list: { price: null },
    ppid: null,
    name:null,
    image_id:null
  }

  ngOnInit() {
    super.ngOnInit();
    //this.myFunction();

  }

  onFileSelect(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);

  }
  addCart(id, name, image_id) {
    let _self = this;
    _self.form.error=null;
    console.log("In catalogue addCart/......"+id+" "+name+" "+image_id)
    this.priceForm.ppid = id;
    this.priceForm.name=name;
    this.priceForm.image_id=image_id;
    console.log("ppid=" + this.priceForm.ppid + "------" +this.priceForm.name+"-----"+this.priceForm.image_id);
    
    let cartForm = {
      productName: name,
      image_id: image_id,
      quantity : 1
      
    }
    console.log(cartForm.productName+" "+cartForm.image_id+" "+cartForm.quantity);
    let url = _self.locator.endpoints.SHOPPING;
    _self.http.post(url + '/addItem', cartForm, function (res, error) {
     
      if(res.success){
      _self.form.data = res.result.data;

      console.log("addcart"+ _self.form.data.id);

      _self.form.message = "Product Added Successfully!!";
    }
    
    if (!res.success) {
      _self.form.error=true;
      _self.form.message = "Product Already Added !!";
    }
    })
    // _self.searchingProduct(_self.form.data.id);
  }

//add too wish list-------------->
addWishList(id, name, image_id) {
  let _self = this;
  _self.form.error=null;
  console.log("In catalogue addCart/......"+id+" "+name+" "+image_id)
  this.priceForm.ppid = id;
  this.priceForm.name=name;
  this.priceForm.image_id=image_id;
  console.log("ppid=" + this.priceForm.ppid + "------" +this.priceForm.name+"-----"+this.priceForm.image_id);
  
  let cartForm = {
    productName: name,
    image_id: image_id,
    quantity : 1
    
  }
  console.log(cartForm.productName+" "+cartForm.image_id+" "+cartForm.quantity);
  let url = _self.locator.endpoints.WISHLIST;
  _self.http.post(url + '/addItem', cartForm, function (res, error) {
   
    if(res.success){
    _self.form.data = res.result.data;

    console.log("addcart"+ _self.form.data.id);

    _self.form.message = "Product Added to Wish-List !!";
  }
  if (!res.success) {
    _self.form.error=true;
    _self.form.message = "Product Already Added to Wish-List !!";
  }
  })
  // _self.searchingProduct(_self.form.data.id);
}

forward1(id){
  this.router.navigateByUrl("/productview/"+id)
}
  CatalogForm = {
    data: {

    },
    list: []
  }

  populateForm(form, data) {

    form.id = data.id;
    form.name = data.name;
    form.image_id = data.image_id;
    form.product_id = data.product_id;
    form.description = data.description;
    form.category_id = data.category_id;
    form.subcategory_id = data.subcategory_id;
    form.category_name = data.category_name;
    form.subcategory_name = data.subcategory_name;
    form.specification = data.specification;

  }
  imageId: null
  // saveCatalogue() {
  //   let _self = this;

  //   let url = _self.locator.endpoints.CATALOGUE;
  //   _self.http.post(url + '/save', _self.form.data, function (res, error) {
  //     if (error) {
  //       return;
  //     }
  //     _self.form.message = "Data is saved";
  //     _self.imageId = res.result.data
  //     _self.saveimage(_self.imageId)
  //   })
  // }
  // saveimage(imageId) {
  //   let _self = this;
  //   let url = _self.locator.endpoints.CATALOGUE;
  //   const formData = new FormData();
  //   formData.append('file', this.fileToUpload);
  //   _self.http.post(url + '/productimage/' + imageId, formData, function (res, error) {
  //     if (error) {
  //       return;
  //     }

  //     _self.imageId = res.result.data

  //   })
  //   super.search();
  // }
  //  forms={
  //   price1:null,
  //   pkey:0,
  //   gst:0
  // }
  // searchingProduct(pkey) {
  //   var _self = this;
  //   _self.forms.pkey=pkey;

  //   console.log("searchingProduct "+_self.forms.pkey);

  //   let cartForm = {
  //     product_id:this.priceForm.ppid
  //   }
  //   console.log("ID----------" + this.priceForm.ppid);
  //   let url2 = _self.locator.endpoints.PRODUCTPRICE;
  //   _self.http.post(url2 + '/search', cartForm, function (res, error) {
  //     if (error) {
  //       return;
  //     }
  //     _self.forms.price1 = res.result.data[0].price;
  //     _self.forms.gst = res.result.data[0].gst;
  //   _self.saveAgain(res.result.data[0].price,_self.forms.pkey, res.result.data[0].gst);
  //         console.log("Priceeeee----" + _self.forms.price1+"---------------ogp"+res.result.data[0].price);
  //   })

  // }

  // saveAgain(price,pkey,gst){
  //   let _self=this;
  //   console.log("saveagain"+pkey);
  //   let cartForm1 = {
  //     id:_self.form.data,
  //     productName: this.priceForm.name,
  //     image_id: this.priceForm.image_id,
  //     price: price,
  //     gst:gst
  //   }
  //   console.log("cartform dobara save-----"+_self.forms.price1);
  //   let url = _self.locator.endpoints.SHOPPING;
  //   _self.http.post(url + '/save', cartForm1, function (res, error) {
  //     if (error) {
  //       return;
  //     } 
  //     _self.form.data.id = res.result.data;
  //     _self.form.message = "Data is saved";

  //   })


  // }

  // searching() {
  //   var _self = this;
  //   console.log("ID----------" + this.priceForm.ppid);
  //   let url2 = _self.locator.endpoints.PRODUCTPRICE;
  //   _self.http.get(url2 + '/getByPId/' + _self.priceForm.ppid, function (res, error) {
  //     if (error) {
  //       return;
  //     }
  //     _self.priceForm.list.price = res.result.data.price;
  //     console.log("Priceeeee----" + _self.priceForm.list.price);
  //   })

  //   let cartForm = {
  //     id:this.priceForm.ppid,
  //     name: this.priceForm.name,
  //     image_id: this.priceForm.image_id,
  //     price: _self.priceForm.list.price
  //   }
  //   let url = _self.locator.endpoints.SHOPPING;
  //   _self.http.post(url + '/save', cartForm, function (res, error) {
  //     if (error) {
  //       return;
  //     }
  //     _self.form.data.id = res.result.data;
  //     _self.form.message = "Data is saved";

  //   })

  // }

}
