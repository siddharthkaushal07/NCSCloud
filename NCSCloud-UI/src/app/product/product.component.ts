import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { callbackify } from 'util';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { HttpServiceService } from '../common/service/http-service.service';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent extends BaseListCtl {
  fileToUpload: File;

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, public https :HttpServiceService) {
    super(locator.endpoints.PRODUCT, locator, route);
   this.getAllSubCat();
  }


  
  onFileSelect(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);

  }

form1={
  specification:[]
}
  populateForm(form, data) {

    form.id = data.id;
    form.name = data.name;
    form.description = data.description;
    form.mrp = data.mrp;
    form.discount = data.discount;
    form.price = data.price;
    form.gst = data.gst;
    form.category_id = data.category_id;
    form.subcategory_id = data.subcategory_id;
    form.category_name = data.category_name;
    form.subcategory_name = data.subcategory_name;

    form.specification = data.specification;
    form.model_no = data.model_no;
    form.model_year = data.model_year;


  }

  getId(id:[])
  {
    let ids:[]=id;
    for(let i=0;i<ids.length;i++){
      console.log("i============"+ids[i])
    }
  }

  prod_id;
  save(){
    
    var _self = this;

    _self.form.error = false;
    _self.form.message = "";
    _self.form.inputerror = {};
let url =_self.locator.endpoints.PRODUCT;
    this.locator.http.post(url+'/save', this.form.data, function (res, error) {

      if (error) {
        return;
      }

      _self.form.error = !res.success;
      _self.form.message = res.result.message;
                   _self.prod_id   = res.result.data;
      if (res.success) {
        _self.form.message = "Data is saved";
       
      } else {
        _self.form.inputerror = res.result.inputerror;
      }
      console.log('FORM', _self.form);
      _self.saveimage(_self.prod_id)
    });


  }

  SubForm ={
    list : []
  }
  getAllSubCat(){
    let _self = this;
    let url = _self.locator.endpoints.SUBCATEGORY;
    let catForm ={
      
    }
    _self.https.post(url+"/search/0" , catForm,function (res, error) {

      if (error) {
        return;
      }

      _self.SubForm.list = res.result.data
  
});
}
  getSubCat(){
    let _self =this;
    let url = this.locator.endpoints.SUBCATEGORY;
    let catForm ={
      category_id : _self.form.data["category_id"] 
    }
    _self.https.post(url+"/search/0" , catForm,function (res, error) {

      if (error) {
        return;
      }

      _self.SubForm.list  = res.result.data;
  
});
}
  public end = "http://localhost:9101/NCSECOM/Chart/getPic";
  public sid = localStorage.getItem('sessionId');
  public session = ";jsessionid=" + this.sid;



  imageId : null
  saveCatalogue(){
    let _self = this;
    
    let url = _self.locator.endpoints.CATALOGUE; 
    _self.https.post(url+'/save', _self.form.data, function (res ,error){
      if (error) {
        return;
      }
      _self.form.message = "Data is saved";
     _self.imageId =res.result.data
     _self.saveimage(_self.imageId)
    })
  }

  saveimage(imageId) {
    let _self = this;
    let url = _self.locator.endpoints.PRODUCT; 
    const formData = new FormData();
    formData.append('file', this.fileToUpload);
    _self.https.post(url+'/productimage/'+imageId,formData , function (res ,error){
      if (error) {
        return;
      }

     _self.imageId =res.result.data
   
    })
    super.search();
  }




}
