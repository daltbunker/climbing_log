import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RstApiService } from 'src/app/services/rst-api.service';

@Component({
  selector: 'app-ascent-form',
  templateUrl: './ascent-form.component.html',
  styleUrls: ['./ascent-form.component.sass']
})
export class AscentFormComponent implements OnInit {

  private climbId: number | undefined;
  private ascentId: number | undefined;
  public climbName: string | undefined;
  public formType: string | undefined;
  public ascentForm = new FormGroup({
    dateControl: new FormControl(new Date()),
    attemptsControl: new FormControl(''),
    commentControl: new FormControl('')
  });
  constructor(
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private rstApiService: RstApiService
  ) { }

  ngOnInit(): void {
    this.climbName = this.data.name;
    this.formType = this.data.formType;
    if (this.formType === 'edit') {
      this.ascentId = this.data.ascentId;
      this.ascentForm.setValue({
        dateControl: new Date(this.data.date),
        attemptsControl: this.mapAttempt(this.data.attempts),
        commentControl: this.data.comment
      });
    } else {
      this.climbId= this.data.climbId;
    }
  }

  onSubmit(): void {
    if (this.ascentForm.valid) {
      const ascent = {
        attempts: this.ascentForm.value.attemptsControl,
        date: this.ascentForm.value.dateControl,
        comment: this.ascentForm.value.commentControl,
      }
      if (this.formType === 'edit') {
        this.saveAscent(ascent);
      } else {
        this.logAscent(ascent);
      }
    }
  }

  logAscent(ascent: any): void {
    if (this.climbId) {
      // TODO: don't hardcode user
      this.rstApiService.addAscent(ascent, this.climbId, 'climber1')
        .subscribe(resp => {
          alert('Ascent added!')
          this.data.submitLogEmitter.emit(true);
        })
    }
  }

  saveAscent(ascent: any): void {
    if (this.ascentId) {
      this.rstApiService.updateAscent(ascent, this.ascentId)
        .subscribe(resp => {
          alert('Ascent updated!')
          this.data.submitLogEmitter.emit(true);
        })
    }
  }

  mapAttempt(attempt: string): string {
    switch(attempt) {
      case('onsight'):
        return 'ONSIGHT';
      case('flash'):
        return 'FLASH';
      case('second go'):
        return 'SECOND_GO';
      case('+3 attempts'):
        return 'PLUS_3';
      default:
        return '';
    }
  }
}
