import { OnInit } from '@angular/core';
import { ServiceLocatorService } from './service/service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { GlobalConstants } from './GlobalConstants ';
import { format } from 'url';

/**
 * Base controller inherited by all controller classes of the application
 */
export class BaseCtl implements OnInit {

  public NULL_VALUE =-100;
  

  //Rest endpoints for web service
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


  /**
   * Reset form
   */
  resetForm(){
    this.form.error = false;
    this.form.message = "";
    this.form.data =  { id: null };
    this.form.inputerror = {};
  }

  /**
   * Reset search from
   */
  resetSearchForm(){
    this.form.searchError = false;
    this.form.searchMessage = "";
    this.form.searchParams =  {};
  }

  /**
   * Initialize services 
   * 
   * @param serviceLocator 
   * @param route 
   */
  constructor(public endpoint: string, public locator: ServiceLocatorService,
    public route: ActivatedRoute) {
    var _self = this;
    _self.initApi(endpoint);
    _self.form.data.id = 0;

    /**
     * Get primary key from path variale
     */
    locator.getPathVariable(route, function (params) {
      _self.form.data.id = params["id"];
      if(_self.form.data.id){
        _self.get(_self.form.data.id);
      }
    })
  }

 
 
  /**
   * Initialize component
   */
  ngOnInit() {
    this.preload();
// this.test();
  }


 
  /**
   * Get record by primary key id and store in _self.form.data
   * @param id 
   */
  get(id, callback? ) {

    var _self = this;

    _self.resetForm();

    if (!id || id == 0) {
      if(callback){
        callback();
      }
      return;
    }

    let url = _self.api.get + "/" + id;

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
   * Save this.form.data using post method
   */
  save(callback?) {

    console.log("inside save method--------------------------")
    var _self = this;

    _self.form.error = false;
    _self.form.message = "";
    _self.form.inputerror = {};

    this.locator.http.post(this.api.save, this.form.data, function (res, error) {

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
   * Populate HTML form data
   * Overridden by child classes.
   * 
   * @param form 
   * @param data 
   */
  populateForm(form, data) {
    form.id = data.id;
    form.skey= data.skey;
  }

  /**
   * Loded preload data
   */
  preload() {
    var _self = this;

    _self.form.error = false;
    _self.form.message = "";

    this.locator.http.get(_self.api.preload, (res, error) => {
      
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
   * Delete a record and execute callback after deletion 
   * 
   * @param id 
   * @param callback 
   */
  delete(id, callback?) {

    var _self = this;
    _self.form.error = false;
    _self.form.message = "";   

    let flag = confirm("Are you sure ?");
    if (!flag) {
      return;
    }

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

  /**
   * Forward to page
   * @param page 
   */
  forward(page) {
    this.locator.forward(page);
  }


  /**
   * Image timestap 
  */
  public imageTimestamp =0;

  changeDoc = function() {
    this.imageTimestamp = (new Date()).getTime();
  }    
  /**
   * Get doc thumbnail
   * @param skey 
   */
  getDocThumbnail(skey) {
    if (!skey) {
      skey = 0;
    }
    return GlobalConstants.DOC_API + "th/"  + skey + "?t=" + this.imageTimestamp;;
  }

  /**
   * Get application doc
   * @param skey 
   */
  getDoc(skey) {
    if (!skey) {
      skey = 0;
    }
    return GlobalConstants.DOC_API  + skey + "?t=" +  this.imageTimestamp;;
  }

  //Return refrence if current controller
  getSelf() {
    return this;
  }

  /**
   * Convert object into string
   * 
   * @param obj 
   */
  toString(obj){
    JSON.stringify(obj);
  }

}
