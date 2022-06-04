import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { BaseListCtl } from '../common/base-list.component';
import { BaseCtl } from '../common/base.component';
import { ServiceLocatorService } from '../common/service/service-locator.service';

@Component({
  selector: 'app-productprice',
  templateUrl: './productprice.component.html',
  styleUrls: ['./productprice.component.css']
})
export class ProductpriceComponent extends BaseListCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private http: HttpClient) {
    super(locator.endpoints.PRODUCTPRICE, locator, route);
  }
  populateForm(form, data) {

    form.id = data.id;
    form.name = data.name;
    form.mrp = data.mrp;
    form.discount = data.discount;
    form.price = data.price;
    form.gst = data.gst;
    form.product_id = data.product_id;
    form.model_id = data.model_id;
    form.model_name = data.model_name;
    form.model_no = data.model_no;

  }

  // ngOnInit() {
  //   super.ngOnInit();
  //   this.filterByPrice();
  // }

  filterForm = {
    data: null,
    filter: null
  }

  filterByPrice() {
    var _self = this;
    _self.form.searchError = false;
    _self.form.searchMessage = "";
    _self.form.message = "";

    console.log("filterForm.filter=" + this.filterForm.filter);

    this.locator.http.post("http://localhost:9102/NCSCloud/ProductPrice/getByFilter/" + _self.filterForm.filter, _self.filterForm.data, function (res, error) {
      if (error) {
        return;
      }

      _self.form.searchError = !res.success;
      _self.form.searchMessage = res.result["message"];

      _self.form.list = res.result.data
      console.log(res.result.data)

      if (_self.form.list.length == 0) {
        _self.form.message = "No record found";
        _self.form.error = true;
      }
    })
  }
}
