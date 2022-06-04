import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-productmodel',
  templateUrl: './productmodel.component.html',
  styleUrls: ['./productmodel.component.css']
})
export class ProductmodelComponent extends BaseListCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.PRODUCTMODEL, locator, route);
  }
  populateForm(form, data) {

    form.id = data.id;
    form.name = data.name;
    form.model_no = data.model_no;
    form.model_year = data.model_year;
    form.product_id = data.product_id;

  }




}
