import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Ascent } from 'src/app/models/Ascent.model';
import { RstApiService } from 'src/app/services/rst-api.service';

@Component({
  selector: 'app-ascent-form',
  templateUrl: './ascent-form.component.html',
  styleUrls: ['./ascent-form.component.sass']
})
export class AscentFormComponent implements OnInit {

  private climbId: number | undefined;
  public ascentForm = new FormGroup({
    dateControl: new FormControl(''),
    attemptsControl: new FormControl(''),
    commentControl: new FormControl('')
  })
  constructor(
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private rstApiService: RstApiService
  ) { }

  ngOnInit(): void {
    this.climbId = this.data.climbId
  }

  logAscent(): void {
    if (this.ascentForm.valid) {
      const attempts = this.ascentForm.value.attemptsControl;
      const date = this.ascentForm.value.dateControl;
      const comment = this.ascentForm.value.commentControl;
      const ascent: Ascent = {
        climb_id: this.data.climbId,
        attempts: attempts,
        date: date,
        comment: comment
      }
      if (this.climbId) {
        // TODO: don't hardcode user
        this.rstApiService.addAscent(ascent, this.climbId, 'climber1')
          .subscribe(resp => {
            alert('Ascent added!')
            this.data.submitLogEmitter.emit(true);
          })
      }
    }
  }
}
