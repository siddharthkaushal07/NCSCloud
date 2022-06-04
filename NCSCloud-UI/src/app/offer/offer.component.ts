import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent extends BaseListCtl {
  

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.OFFER, locator, route);
    this.productId= localStorage.getItem("productId");
  }
  productId;

 populateForm(form, data) {
    
    form.id = data.id;
    form.name = data.name;
    form.productId=data.productId;
    form.discount = data.discount;
    form.price=data.price;

    
  } 

}
