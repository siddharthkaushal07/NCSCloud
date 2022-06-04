import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseCtl } from '../common/base.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-bussiness-setting',
  templateUrl: './bussiness-setting.component.html',
  styleUrls: ['./bussiness-setting.component.css']
})
export class BussinessSettingComponent extends BaseCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.GSTREGISTRATION, locator, route);
}

 

}
