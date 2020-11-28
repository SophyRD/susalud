import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { AutoevalucionXprocesoUpdateComponent } from 'app/entities/autoevalucion-xproceso/autoevalucion-xproceso-update.component';
import { AutoevalucionXprocesoService } from 'app/entities/autoevalucion-xproceso/autoevalucion-xproceso.service';
import { AutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';

describe('Component Tests', () => {
  describe('AutoevalucionXproceso Management Update Component', () => {
    let comp: AutoevalucionXprocesoUpdateComponent;
    let fixture: ComponentFixture<AutoevalucionXprocesoUpdateComponent>;
    let service: AutoevalucionXprocesoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [AutoevalucionXprocesoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AutoevalucionXprocesoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AutoevalucionXprocesoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AutoevalucionXprocesoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AutoevalucionXproceso(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AutoevalucionXproceso();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
