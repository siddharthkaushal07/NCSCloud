import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-body',
  templateUrl: './list-body.component.html',
  styleUrls: ['./list-body.component.css']
})
export class ListBodyComponent implements OnInit {

  @Input() $parent = {};

  constructor() { }

  ngOnInit() {
  }

}
