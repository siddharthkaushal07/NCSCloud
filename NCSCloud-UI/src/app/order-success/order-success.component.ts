import { LocationStrategy } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { HttpServiceService } from '../common/service/http-service.service';
import { ServiceLocatorService } from '../common/service/service-locator.service';
import { UtilserviceService } from '../utilservice.service';

@Component({
  selector: 'app-order-success',
  templateUrl: './order-success.component.html',
  styleUrls: ['./order-success.component.css']
})
export class OrderSuccessComponent extends BaseListCtl {

  constructor(private location: LocationStrategy,public locator: ServiceLocatorService, public route: ActivatedRoute, public http: HttpServiceService,private razorpayService: UtilserviceService, private cd:  ChangeDetectorRef) 
  {
  super(locator.endpoints.SHOPPING, locator, route); 

    history.pushState(null, null, window.location.href);  
    this.location.onPopState(() => {
      history.pushState(null, null, window.location.href);
    });  
   }

  

  msg="Your Order has been successfully placed!!"

  oid;
    setId(id){
let _self=this;
_self.oid=id;
    }
}
