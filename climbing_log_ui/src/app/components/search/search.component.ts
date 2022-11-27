import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { map, Observable, startWith } from 'rxjs';
import { SearchModel } from 'src/app/models/Search.model';
import { RstApiService } from 'src/app/services/rst-api.service';
import { AscentFormComponent } from '../ascent-form/ascent-form.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.sass']
})
export class SearchComponent implements OnInit {

  @Input() data: Array<{value: string, type: string}> = [];
  public filteredData: Observable<SearchModel[]> | undefined;
  public searchForm = new FormGroup({
    searchControl: new FormControl('')
  }) 
  public searchResults: Array<any> = [];
  public searchResultKeys: Array<string> = [];
  private searchType = '';

  constructor(
    public rstApiService: RstApiService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.filteredData = this.searchForm.get('searchControl')?.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    ); 
  }

  private _filter(value: string): {value: string, type: string}[] {
    const filterValue = value.toLowerCase();
    return this.data.filter(d => d.value.toLowerCase().includes(filterValue));
  }

  onSearch(): void {
    if (this.searchForm.value.searchControl) {
      const searchValue = this.searchForm.value.searchControl;
      if (this.searchType === 'climb') {
        this.rstApiService.getClimbsByName(searchValue)
          .subscribe(resp => {
            this.searchResults = resp.map((result: any) => {
              const { name } = result;
              const grade = this.translateGrade(result.grade);
              return {
                name,
                grade
              }
            })
            this.searchResultKeys = Object.keys(this.searchResults[0]);
          })
      } else {
        this.rstApiService.getLocationsByParam(searchValue)
          .subscribe(resp => {
            this.searchResults = resp.map((result: any) => {
              const { id, country, state, sector, area } = result;
              return {
                id,
                country,
                state,
                sector,
                area
              }
            })
            this.searchResultKeys = Object.keys(this.searchResults[0]);
            this.searchResultKeys.shift();
          })
      }
    } else {
      // focus search box
    }
  }

  onBreadcrumbClick(): void {

  }

  getClimbs(locationId: number): void {
    this.rstApiService.getClimbsByLocation(locationId)
      .subscribe(resp => {
        this.searchResults = resp.map((result: {name: string, grade: string}) => {
          const name = result.name;
          const grade = this.translateGrade(result.grade)
          return {
            name,
            grade
          }
        })
        this.searchResultKeys = Object.keys(this.searchResults[0]);
      })
  }

  setSearchType(type: string): void {
    this.searchType = type;
  }

  translateGrade(grade: string): string { // TODO: make this global
    if (grade.includes('R')) {
      const routeMap = ['5.4', '5.5', '5.6', '5.7', '5.8',
                        '5.9', '5.10a', '5.10b', '5.10c',
                        '5.10d', '5.11a', '5.11b', '5.11c',
                        '5.11d', '5.12a', '5.12b', '5.12c', 
                        '5.12d', '5.13a', '5.13b', '5.13c',
                        '5.13d', '5.14a', '5.14b', '5.14c',
                        '5.14d', '5.15a', '5.15b', '5.15c',
                      ]
      const gradeIndex = parseInt(grade.split('R')[1]);
      return routeMap[gradeIndex];
    } else if (grade.includes('B')) {
      return grade.replace('B', 'V'); 
    }
    return '';
  }

  openLogModal(climb: any): void {
    const dialogRef = this.dialog.open(AscentFormComponent, {
      width: '400px',
      minHeight: '300px'
    })
  }

}
