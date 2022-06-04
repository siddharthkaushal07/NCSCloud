
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseCtl } from '../base.component';
import { GlobalConstants } from '../GlobalConstants ';
import { EventService } from '../service/event.service';
import { ServiceLocatorService } from '../service/service-locator.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent extends BaseCtl {
  constructor(public locator: ServiceLocatorService,
    private eService: EventService, public route: ActivatedRoute) {
    super(locator.endpoints.AUTH, locator, route);
  }


  ngOnInit() {
    let _self = this;
    _self.form.data["orgId"] =  GlobalConstants.DEFAULT_ORG_ID;
  }

  populateForm(form, data) {
    super.populateForm(form, data);
    form.data.firstName = data.firstName;
    form.data.lastName = data.lastName;
    form.data.login = data.login;
    form.data.password = data.password;
  }

  isLogin = false;
  
  signup() {

    var _self = this;
    let url = this.locator.endpoints.getEndpoint(this.locator.endpoints.AUTH, "sso/signup");

    _self.form.data["orgId"] =   GlobalConstants.DEFAULT_ORG_ID;
    _self.form.data["role"] =  GlobalConstants.DEFAULT_ROLE;;

    this.locator.http.post(url, this.form.data, function (res, error) {

       console.log('response', res);
      _self.form.message = '';
      _self.form.inputerror = {};

      //console.log("1");
      if(res.success){
        _self.isLogin = true;
      }else{
      _self.form.error = !res.success;
      _self.form.message = res.result.message;
      _self.form.inputerror = res.result.inputerror;
      }
    });
  }
}
