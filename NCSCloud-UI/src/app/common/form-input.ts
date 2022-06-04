import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';

@Component({
  selector: 'form-input',
  template: `
  <label for="abc" 
    class="col-sm-1 col-form-label col-form-label-sm">
    {{eid}}<i class="fas fa-asterisk" style="color:red"></i>
  </label>
  <div class="col">
  <input id="abc" name="abc" placeholder="eid" type="text"
  class="form-control form-control-sm" required >
</div>
  `
})
export class FormInputComponent implements OnInit {

  @Input() eid: string = "xyz";
  @Input() label: string = "";
  @Input() ele: any;

  constructor() {
    console.log("inside form input", this.eid);
  }
  ngOnInit() {

  }
}