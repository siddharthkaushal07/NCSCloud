import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { GlobalConstants } from '../common/GlobalConstants ';
import { ServiceLocatorService } from '../common/service/service-locator.service';
import { EndpointServiceService } from '../common/service/endpoint-service.service';


@Component({
  selector: 'app-shipping',
  templateUrl: './shipping.component.html',
  styleUrls: ['./shipping.component.css']
})
export class ShippingComponent extends BaseListCtl {
  serviceLocator: any;
  public NULL_VALUE =-100;
  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.SHIPPING, locator, route);
    var _self = this;
    // _self.initApi(endpoint);
  

  }

  public api = {
    endpoint: null,
    get: null,
    save: null,
    search: null,
    delete: null,
    preload: null
  }

  //Initiaize end point suburls 
  initApi(ep) {
    this.api.endpoint = ep;
    this.api.get = ep + "/get";
    this.api.save = ep + "/save";
    this.api.search = ep + "/search";
    this.api.delete = ep + "/delete";
    this.api.preload = ep + "/preload";
    //console.log("API", this.api);
  }

  ngOnInit() {
    super.ngOnInit();
      console.log("ppppppppppppppppppppppppppppp")
      this.search();
      this.preload();
      this.bPreload();
      this. bSearch();
    

  }


  /**
   * Form contains preload data, error/sucess message 
   */
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



  populateForm(form, data) {

    form.id = data.id;
    form.name = data.name;
    form.lastname = data.lastname;
    form.mobileno = data.mobileno;
    form.city = data.city;
    form.address = data.address;
    form.pincode = data.pincode;
    form.state = data.state;


  }

   /**
   * Populate HTML form data
   * Overridden by child classes.
   * 
   * @param form 
   * @param data 
   */
   biPopulateForm(bForm, data) {
    bForm.id = data.id;
    bForm.skey= data.skey;
  }
 
  bPopulateForm(bForm, data) {

    bForm.id = data.id;
    bForm.name = data.name;
    bForm.lastname = data.lastname;
    bForm.mobileno = data.mobileno;
    bForm.city = data.city;
    bForm.address = data.address;
    bForm.pincode = data.pincode;
    bForm.state = data.state;
    bForm.sid = data.sid;


  }

  // bPopulateForm2(bForm, data) {

  //   bForm.sid = data.id;
  //   bForm.name = data.name;
  //   bForm.lastname = data.lastname;
  //   bForm.mobileno = data.mobileno;
  //   bForm.city = data.city;
  //   bForm.address = data.address;
  //   bForm.pincode = data.pincode;
  //   bForm.state = data.state;


  // }

  
  

  // getAdd(id){
  //   let _self=this;
  //   console.log("inside getAdd Shipping---------------");    
  //   console.log("ttttttttttttttt "+id);
  //     if(_self.user.check==true){
  //       console.log("inside getAdd Shipping---------------");    
  //       console.log("ttttttttttttttt "+id);  
  //     }

  //     else{
  //       console.log("false ===== inside getAdd Shipping---------------");    
  // console.log("ttttttttttttttt "+id);
  //     }

  // }

  firstName


  //   test(){
  // //  console.log("local fname======= "+localStorage.getItem(GlobalConstants.getKey('firstName')));
  //  this.firstName= localStorage.getItem(GlobalConstants.getKey('firstName'))
  // console.log("name in shipping add-------- "+this.firstName);  
  // }




  // addBilling(id, name, lastname) {

  //   console.log(id + " " + name + " " + lastname)

  //   let bForm = {
  //     id: id,
  //     name: name,
  //     lname: lastname
  //   }

  // }
  isCheck: boolean=false;
  active(){
    let  _self=this;
   return _self.isCheck=true;
  }






/**
   * Loded preload data
   */
  bPreload() {
    var _self = this;
let url="http://localhost:9102/NCSCloud/Billing/preload/";
    _self.bForm.error = false;
    _self.bForm.message = "";

    this.locator.http.get(url, (res, error) => {
      
      if (error) {
        return;
      }

      _self.bForm.error = !res.success;
      _self.bForm.message = res.result.message;

      if (res.success) {
        _self.bForm.preload = res.result;
      } 

      console.log('FORM', _self.bForm);
    });
  }



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
      }
    });
  }

/**
   * Delete a record and execute callback after deletion 
   * 
   * @param id 
   * @param callback 
   */
 bDelete(id, callback?) {
   console.log("bdlete billing--------------------- "+id)
  this.bSearch();
let url ="http://localhost:9102/NCSCloud/Billing/delete/"+id
  var _self = this;
  _self.bForm.error = false;
  _self.bForm.message = "";   

  let flag = confirm("Are you sure ?");
  if (!flag) {
    return;
  }

  this.locator.http.get(url, function (res, error) {

    if (error) {
      return;
    }

    _self.bForm.error = !res.success;
    _self.bForm.message = res.result.message;

    if (res.success) {
      _self.bForm.message = "Data is deleted";
     
      if (callback) {
        callback();
      }
    }
    _self.bSearch(); 
  });
  this. bSearch();
}






  biForm={sId:null}

  sBox:boolean;
  checkedShipp(event){
    let _self = this;
    if ( event.target.checked ) {
      console.log("is checked--------==========")
    
     _self.sBox = true;
     console.log( _self.sBox)
    
 }
 else{
  let _self = this;
  console.log("is UN checked--------==========")
  _self.biForm.sId = "";
     _self.sBox = false;
     console.log( _self.sBox)
 }

  }

  bBox:boolean;
  checkedBill(e){
    let _self = this;
    if ( e.target.checked ) {
      console.log("bbox is checked--------==========")
      localStorage.removeItem("bid");
      console.log("after unckeck boxx bid is === "+localStorage.getItem("bid"));
    
     _self.bBox = true;
     console.log( _self.bBox)
    
 }
 else{
  let _self = this;
  console.log("bbox is UN checked--------==========")
  localStorage.removeItem("bid");
  console.log("after unckeck boxx bid is === "+localStorage.getItem("bid"));

  _self.bBox = false;
     console.log( _self.bBox)
 }



  }

  checkAdd(b:number){

    // console.log("shipping id ********************** "+a)
    console.log("billing id  ********************** "+b)

  }
  

  getShipping(id) {
    localStorage.setItem("sid", id);
    console.log("sid get from localstorage=====+ "+localStorage.getItem("sid"));
    let _self = this;
    
   
    console.log("inside getshipping component");
      _self.biForm.sId = id;
    console.log("inside shipping component ---shipping id------------->" + _self.biForm.sId)
 
  }


  setB(){
      let _self=this
      _self.sGet(_self.biForm.sId)
  }



sGet(id, callback? ) {

    console.log("billing id---------------->"+id)
    var _self = this;

    _self.resetForm();

    if (!id || id == 0) {
      if(callback){
        callback();
      }
      return;
    }

    let url = "http://localhost:9102/NCSCloud/Shipping/get/" + id;

    this.locator.http.get(url, function (res, error) {
      if (error) {
        return;
      } 
      _self.bForm.error = !res.success;
      _self.bForm.message = res.result.message;

      if (res.success) {
//        console.log("inside get shiiping -----"+ _self.bForm.data.id);
      //  console.log( res.result.data["name"])
        _self.bForm.data.id = id;
        console.log("inside get shiiping -----"+ _self.bForm.data.id);

        _self.bPopulateForm(_self.bForm.data, res.result.data);
        if(callback){
          callback();
        }
      } 
    });
  }
 



  
  bGet(id, callback? ) {

    console.log("billing id---------------->"+id)
    var _self = this;

    //_self.bResetForm();

    if (!id || id == 0) {
      if(callback){
        callback();
      }
      return;
    }

    let url = "http://localhost:9102/NCSCloud/Billing/get/" + id;
    //let url=_self.endpoint.BILLING + "/get/" +id;
    this.locator.http.get(url, function (res, error) {
      if (error) {
        return;
      } 
      _self.bForm.error = !res.success;
      _self.bForm.message = res.result.message;

      if (res.success) {
//        console.log("inside get shiiping -----"+ _self.bForm.data.id);
      //  console.log( res.result.data["name"])
        _self.bForm.data.id = id;
        console.log("inside get shiiping -----"+ _self.bForm.data.id);

        _self.bPopulateForm(_self.bForm.data, res.result.data);
        if(callback){
          callback();
        }
      } 
    });
  }
 

  

  bSave(callback?){



    var _self = this;
 let url="http://localhost:9102/NCSCloud/Billing/save"
    _self.bForm.error = false;
    _self.bForm.message = "";
    _self.bForm.inputerror = {};
    console.log("tttttttttttttt"+_self.bForm.data["sid"])
    //  _self.bForm.data["sid"]=_self.bForm.data.id
    //_self.bForm.data.id=null;

    

    //_self.bForm.data.id="";
    this.locator.http.post(url, this.bForm.data, function (res, error) {

      if (error) {
        return;
      }

      _self.bForm.error = !res.success;
      _self.bForm.message = res.result.message;

      if (res.success) {
        _self.bForm.message = "Data is saved";
        if (callback) {
          callback();
        }
      } else {
        _self.bForm.inputerror = res.result.inputerror;
      }
      _self.bSearch(); 
      console.log('FORM', _self.bForm);
    });
//    _self.search();
  }

  public f={
    submitted:true
  };
  bResetForm(){

    console.log("inside reset form in shipping--------------------");
    let _self=this;
    this.bForm.error = false;
    this.bForm.message = "";
    this.bForm.data =  { id: null };
    this.bForm.inputerror = {};
    _self.bForm.data={id:null}
    _self.f.submitted=false;
  }



  // ----------------------------------------------------->


  /**
   * Save this.form.data using post method
   */
   save(callback?) {
    var _self = this;
    let url= _self.locator.endpoints.SHIPPING 
    //"http://localhost:9102/NCSCloud/Shipping/save"
    console.log("inside save method--------------------------")
    

    _self.form.error = false;
    _self.form.message = "";
    _self.form.inputerror = {};

    this.locator.http.post(url+"/save/", this.form.data, function (res, error) {

      if (error) {
        return;
      }

      _self.form.error = !res.success;
      _self.form.message = res.result.message;

      if (res.success) {
        _self.form.message = "SuccessFully Saved!!";
        if (callback) {
          callback();
        }
      } else {
        _self.form.inputerror = res.result.inputerror;
      }

      _self.search();
      console.log('FORM', _self.form);
    });
  }

  validate() {
    return this.validateForm(this.form.data);
  }

/**
   * Override by childs 
   * 
   * @param form 
   */
 validateForm(form) { }

  /**
   * Delete a record and execute callback after deletion 
   * 
   * @param id 
   * @param callback 
   */
   delete(id, callback?) {
    
    console.log("shipping delete---------------- "+id);
    
    let url ="http://localhost:9102/NCSCloud/Shipping/delete/"+id
    var _self = this;
    _self.form.error = false;
    _self.form.message = "";   

    let flag = confirm("Are you sure ?");
    if (!flag) {
      return;
    }

    this.locator.http.get(url , function (res, error) {

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
      _self.search();
    });
  }

  /**
   * Forward to page
   * @param page 
   */
  forward(page) {
    this.locator.forward(page);
  }



  
  /**
   * Get record by primary key id and store in _self.form.data
   * @param id 
   */
   get(id, callback? ) {
console.log("inside shipping get----------------")
    var _self = this;

    _self.resetForm();

    if (!id || id == 0) {
      if(callback){
        callback();
      }
      return;
    }
    let url = "http://localhost:9102/NCSCloud/Billing/get/" + id;  
//    let url = _self.endpoint.SHIPPING + "/get/" +id;
    this.locator.http.get(url, function (res, error) {
      if (error) {
        return;
      }
      _self.form.error = !res.success;
      _self.form.message = res.result.message;

      if (res.success) {
        console.log("inside get basectl-----");
      //  console.log( res.result.data["name"])
        _self.form.data.id = id;
        _self.populateForm(_self.form.data, res.result.data);
        if(callback){
          callback();
        }
      } 
    });
  }



  /**
   * Loded preload data
   */
   preload() {
    var _self = this;

    _self.form.error = false;
    _self.form.message = "";
    let url="http://localhost:9102/NCSCloud/Shipping/preload/";
    this.locator.http.get(url, (res, error) => {
      
      if (error) {
        return;
      }

      _self.form.error = !res.success;
      _self.form.message = res.result.message;

      if (res.success) {
        _self.form.preload = res.result;
      } 

      console.log('FORM', _self.form);
    });
  }

  /**
   * Searhs records 
   */
  search() {
    console.log("BaseCtl shipping search run...-------------")
    var _self = this;

    
    _self.form.searchError = false;
    _self.form.searchMessage = "";    

    console.log("Search Form", _self.form.searchParams);

    let url ="http://localhost:9102/NCSCloud/Shipping/search/" + _self.form.pageNo;

    this.locator.http.post(url, _self.form.searchParams, function (res, error) {

      if (error) {
        return;
      }

      _self.form.searchError = !res.success;
      _self.form.searchMessage = res.result["message"];

      if (res.success) {
        console.log("inside baselistctl success------------->")
        _self.form.list = res.result.data;
        console.log( "llllllllllllllllllllllllllll"+_self.form.list );
        
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

  /**
   * Reset form
   */
   resetForm(){
    this.form.error = false;
    this.form.message = "";
    this.form.data =  { id: null };
    this.form.inputerror = {};
  }


  checkbill:boolean=false;
  checkBill(sid){
    
  }

  saveBill(){
    let _self=this;
    let url = "http://localhost:9102/NCSCloud/Billing/bGet/" + _self.biForm.sId;

    this.locator.http.get(url, function (res, error) {
      if (error) {
        return;
      } 
      _self.bForm.error = !res.success;
      _self.bForm.message = res.result.message;

      if (res.success) {
         res.result.data
        
      }

    
    });
    }

    


    getB(id){
      //for click billing radio than save billing database with shipping id
      console.log("billing id after check radio===+ "+ id)

      localStorage.setItem("bid",id);

    }

    getBillingList(){

    }
}
