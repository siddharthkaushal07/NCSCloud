import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GlobalConstants } from '../GlobalConstants ';
import { ServiceLocatorService } from '../service/service-locator.service';

@Component({
  selector: 'app-app-info',
  templateUrl: './app-info.component.html',
  styleUrls: ['./app-info.component.css']
})
export class AppInfoComponent implements OnInit {

  public locator = null;
  public data = {
    baseApi : "",
    docApi : "",
  }

  constructor(public loc: ServiceLocatorService,
    public route: ActivatedRoute) {
    this.locator = loc;
  }

  ngOnInit() {
    this.data.baseApi =  GlobalConstants.SERVER_URL;
    this.data.docApi =  GlobalConstants.DOC_API;
  }

}
