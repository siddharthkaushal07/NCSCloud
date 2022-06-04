import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseCtl } from '../base.component';
import { GlobalConstants } from '../GlobalConstants ';
import { ServiceLocatorService } from '../service/service-locator.service';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent extends BaseCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.AUTH, locator, route);
    this.myProfile();
  }

  ngOnInit() {
  }

  populateForm(form, data) {
    form.id = data.id;
    form.name = data.name;
    form.phone = data.phone;
    form.gender = data.gender;
    form.dob = data.dob;
    form.email = data.email;
    //form.password = data.password;
    //form.status = data.status;
    //form.role = data.role;
    console.log('----->', form.phone);

  }

  /**
   * Change password form 
   */
  public cpForm = {
    error: false,
    message: "",
    oldPassword: "",
    newPassword: "",
    confirmPassword: "",
    inputerror: {
      oldPassword: "",
      newPassword: "",
      confirmPassword: ""
    }
  }

  /**
   * Get user profile 
   */
  myProfile() {

    var _self = this;
    
    let url = this.locator.endpoints.AUTH + "/sso/profile/" + localStorage.getItem(GlobalConstants.getKey("skey"));

    //let url = this.locator.endpoints.getEndpoint(this.locator.endpoints.USER, "myProfile");
    
    this.locator.http.get(url, function (res, error) {
      if (error) {
        console.log("---error--->", error)
        // alert("Server Error:" + error);
        return;
      }

      _self.form.message = '';
      _self.form.inputerror = {};

      _self.form.error = !res.success;
      if (_self.locator.dataValidator.isNotNullObject(res.result.message)) {
        _self.form.message = res.result.message;
      }

      if (_self.locator.dataValidator.isNotNullObject(res.result.inputerror)) {
        _self.form.inputerror = res.result.inputerror;
      }
      if (_self.locator.dataValidator.isTrue(res.success)) {
        console.log("res.result.data", res.result.data)
        _self.form.data = res.result.data;

      }
    });
  }

  /**
   * update user profile
   */
  updateProfile() {
    let _self = this;
    let authKey = localStorage.getItem(GlobalConstants.getKey('skey'));
    //let url = this.locator.endpoints.getEndpoint(this.locator.endpoints.USER, "updateMyprofile");
    let url = this.locator.endpoints.AUTH + "/sso/profile/" + authKey;

    this.locator.http.post(url, this.form.data, function (res, error) {
      if (error) {
        return;
      }
      _self.form.error = !res.success;
      _self.form.message = res.result.message;
      _self.form.inputerror= {};
      if (_self.locator.dataValidator.isNotNullObject(res.result.inputerror)) {
        _self.form.inputerror = res.result.inputerror;
      }

    });
  }

  /**
   * Change the password 
   * 
   * @returns 
   */
  changePassword() {
    
    let _self = this;

    if (_self.cpForm.newPassword != _self.cpForm.confirmPassword) {
      _self.cpForm.error = true;
      _self.cpForm.message = 'New and confirm password are not matched';
      return;
    }

    let authKey = localStorage.getItem(GlobalConstants.getKey('skey'));
    //let url = this.locator.endpoints.getEndpoint(this.locator.endpoints.USER, "changepassword");
    let url = this.locator.endpoints.AUTH + "/sso/cp/" + authKey;
    this.locator.http.post(url, this.cpForm, function (res, error) {
      _self.cpForm.inputerror = {
        oldPassword : "",
        newPassword : "",
        confirmPassword : "",
      };

      if (error) {
        return;
      }

      _self.cpForm.error = !res.success;
      _self.cpForm.message = res.message;

      if (_self.locator.dataValidator.isNotNullObject(res.result.inputerror)) {
        _self.cpForm.inputerror = res.result.inputerror;
      }

     
      console.log("cpForm", _self.cpForm);


    });

  }

  parseDate(dateString: string): Date {
    if (dateString) {
      return new Date(dateString);
    }
    return null;
  }

  /**
   * Change user image 
   * 
   * @param fileObj 
   */
  onFileChanged(fileObj) {
    var _self = this;
    let authKey = localStorage.getItem(GlobalConstants.getKey('skey'));
    let ssourl = _self.locator.endpoints.AUTH + "/sso/image/" + authKey;
    //let url = _self.locator.endpoints.USER + "/doc/" + this.form.data.id;
    var param = {
      id: this.form.data.id
    };

    //console.log(fileObj);

    this.locator.http.postMutipart(ssourl, param, fileObj, function (res, error) {
      if (error) {
        alert(error);
        return;
      }
      _self.form.error = !res.success;
      _self.form.message = res.result.message;
      if (res.success) {
        _self.form.message = "file is uploaded";
        _self.form["imageId"] = res.result.data.imageId;
        _self.changeDoc();
      }
    });
  }


}
