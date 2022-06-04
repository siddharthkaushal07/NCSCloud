import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-tax',
  templateUrl: './tax.component.html',
  styleUrls: ['./tax.component.css']
})
export class TaxComponent extends BaseListCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.TAX, locator, route);
  }
  populateForm(form, data) {
    
    form.id = data.id;
    form.name = data.name;
    form.rate=data.rate; 

    
  }
 

}
