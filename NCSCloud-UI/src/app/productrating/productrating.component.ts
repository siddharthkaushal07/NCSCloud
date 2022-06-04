import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { BaseListCtl } from '../common/base-list.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-productrating',
  templateUrl: './productrating.component.html',
  styleUrls: ['./productrating.component.css']
})
export class ProductratingComponent extends BaseListCtl {

  public ratingIndex = 2;

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, public http:HttpClient) {
    super(locator.endpoints.PRODUCTRATING, locator, route);
  
  }

  populateForm(form, data) {
    form.id = data.id;
    form.rating = data.rating;
    form.comment = data.comment;
    form.name = data.name;
    form.emailId = data.emailId;
    form.ip_address = data.ip_address;
    form.product_id = data.product_id;

  }

  changeRating(r){
    this.form.data["rating"] = r;
    this.getIpCliente();
  }
  ipAdd : String;
  arr: string[]
  getIpCliente(){
    let _self = this;
    _self.http.get('http://api.ipify.org/?format=jsonp&callback=JSONP_CALLBACK',{responseType: 'text'}).subscribe((res:any)=>{
      _self.ipAdd = res;

     _self.arr = _self.ipAdd.split(':');
  
     _self.arr =_self.arr[1].split('"')
     _self.form.data['ip_address']=_self.arr[1];
   console.log(_self.ipAdd.split(':')) 
  
    }) // ...using post request '
    
}

}
