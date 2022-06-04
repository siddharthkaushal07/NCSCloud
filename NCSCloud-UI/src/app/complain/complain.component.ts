import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-complain',
  templateUrl: './complain.component.html',
  styleUrls: ['./complain.component.css']
})
export class ComplainComponent extends BaseListCtl {
  serviceLocator: any;

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.COMPLAIN, locator, route);
  }
  populateForm(form, data) {
    
    form.id = data.id;
    form.name = data.name;
    form.subject = data.subject;
    form.description = data.description;
    

    
  }


  
  
  


}
