import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormater'
})
export class DateFormaterPipe implements PipeTransform {

  transform(value: any): any {
    let mese = Date.parse(value);
    let date  = new Date(mese);
    console.log(date);
    return date;
  }

}
