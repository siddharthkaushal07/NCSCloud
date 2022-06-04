import { OnInit } from '@angular/core';
import { ServiceLocatorService } from './service/service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { BaseCtl } from './base.component' ;

/**
 * Exetended by List page  
 */
export class BaseListCtl extends BaseCtl {
 
  constructor(public endpoint: string,
    public locator: ServiceLocatorService,
    public route: ActivatedRoute) {
    super(endpoint, locator, route);
  }
  
  
  /**
  * Initialize component
  */
  ngOnInit() {

    this.preload();
    console.log("ssssssssssssssssssshgh")
    this.search();
    
    // console.log(this.form.list," -------eshu---------")

  }
  //Delete a record from list and refresh the list data
  delete(id) {
    let _self = this;
    super.delete(id, function () {
      _self.search();
    });
  }
  
  /**
   * Save a record of the page and execute search
   */
  save() {
    console.log("inside save of basectl------------")
    let _self = this;
    super.save(function(){
      _self.search();
    });
  }


  /**
   * Go to next page of the list 
   */
  next() {
    this.form.pageNo++;
    if (this.form.pageCount == this.form.pageNo) {
      this.form.pageNo--;
    } else {
      this.search();
    }
  }

  /**
   * Go to previous page of the list 
   */
  previous() {
    if (this.form.pageNo > 0) {
      this.form.pageNo--
      this.search();
    }
  }

  //Returns index number of the list page        
  pageIndex(ind) {
    let i =  ind +1 + (this.form.pageNo*this.form.pageSize);
    return i;
  }

}
