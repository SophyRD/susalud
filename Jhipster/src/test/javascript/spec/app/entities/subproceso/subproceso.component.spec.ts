import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProyectoTestModule } from '../../../test.module';
import { SubprocesoComponent } from 'app/entities/subproceso/subproceso.component';
import { SubprocesoService } from 'app/entities/subproceso/subproceso.service';
import { Subproceso } from 'app/shared/model/subproceso.model';

describe('Component Tests', () => {
  describe('Subproceso Management Component', () => {
    let comp: SubprocesoComponent;
    let fixture: ComponentFixture<SubprocesoComponent>;
    let service: SubprocesoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProyectoTestModule],
        declarations: [SubprocesoComponent],
      })
        .overrideTemplate(SubprocesoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubprocesoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubprocesoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Subproceso(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.subprocesos && comp.subprocesos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
