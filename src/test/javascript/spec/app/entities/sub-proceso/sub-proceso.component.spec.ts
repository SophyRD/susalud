import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { SubProcesoComponent } from 'app/entities/sub-proceso/sub-proceso.component';
import { SubProcesoService } from 'app/entities/sub-proceso/sub-proceso.service';
import { SubProceso } from 'app/shared/model/sub-proceso.model';

describe('Component Tests', () => {
  describe('SubProceso Management Component', () => {
    let comp: SubProcesoComponent;
    let fixture: ComponentFixture<SubProcesoComponent>;
    let service: SubProcesoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [SubProcesoComponent],
      })
        .overrideTemplate(SubProcesoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubProcesoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubProcesoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SubProceso(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.subProcesos && comp.subProcesos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
