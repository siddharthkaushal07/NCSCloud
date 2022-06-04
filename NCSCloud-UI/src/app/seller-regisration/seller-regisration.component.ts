import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-seller-regisration',
  templateUrl: './seller-regisration.component.html',
  styleUrls: ['./seller-regisration.component.css']
})
export class SellerRegisrationComponent extends BaseCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.SELLERREGISTRATION, locator, route);
}

}
