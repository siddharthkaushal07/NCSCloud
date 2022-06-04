import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.css']
})
export class BillingComponent extends BaseListCtl {




constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
  super(locator.endpoints.BILLING, locator, route);

    var _self=this;
    locator.getPathVariable(route,function(params){

      // console.log("@@@@@-------------------------"+ this.form.data.id)
     _self.id= _self.form.data.id=params["id"];
        console.log("I Got Id", _self.id);
    })
   }

  id;

  addBill(){

    console.log("addBilling>>>>>>>>>>>>>>>>>>>>> ")
  }

}
