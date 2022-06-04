import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';

@Component({
  selector: 'form-error',
  template: `
  <div *ngIf="form.error && form.message && form.message.trim().length>0" 
     class="alert alert-danger">
    <strong> 
      <button class="btn btn-outline-danger btn-sm" (click)="reset()" >
        <i class="fas fa-times-circle"></i>
      </button>
      &nbsp;{{form.message}}
    </strong>
  </div>
  <div *ngIf="!form.error && form.message && form.message.trim().length>0" 
   class="alert alert-success">
   <strong>
      <button (click)="reset()" class="btn btn-outline-success btn-sm" >
        <i class="fas fa-check-circle"></i>
      </button>&nbsp;
      {{form.message}}
    </strong>
  </div>
  `
})
export class FormErrorComponent implements OnInit {

  @Input() message: string;
  @Input() error: boolean;
  @Input() form: {
    error :false,
    message: ""
  };

  constructor() { }
  ngOnInit() {
    console.log('------------------>');
  }

  reset() {
    this.form.error = false;
    this.form.message = "";
  }

}