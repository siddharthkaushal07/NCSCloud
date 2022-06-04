import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-list-header',
  templateUrl: './list-header.component.html',
  styleUrls: ['./list-header.component.css']
})
export class ListHeaderComponent implements OnInit {

  @Input() title: string = "";

  @Input() $parent = {};

  constructor() { 
  }

  ngOnInit() {
  }
}
