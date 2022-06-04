import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-enquiry',
  templateUrl: './enquiry.component.html',
  styleUrls: ['./enquiry.component.css']
})
export class EnquiryComponent extends BaseListCtl {
  serviceLocator: any;

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.ENQUIRY, locator, route);
  }
  populateForm(form, data) {
    
    form.id = data.id;
    form.name = data.name;
    form.lastname = data.lastname;
    form.enqdate = data.enqdate;
    form.enqmode = data.enqmode;
    form.training=data.training;
    form.mobile = data.mobile;
    form.emailid = data.emailid;
    form.alternatemob = data.alternatemob;
    form.status = data.status;
    form.refby = data.refby;
    form.nfudate = data.nfudate;
    form.gender = data.gender;
    form.dob = data.dob;
    form.address = data.address;
    form.attendby = data.attendby;
    form.remark = data.remark;

    
  }


  
  
  


}
