import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { AutoevalucionUpdateComponent } from 'app/entities/autoevalucion/autoevalucion-update.component';
import { AutoevalucionService } from 'app/entities/autoevalucion/autoevalucion.service';
import { Autoevalucion } from 'app/shared/model/autoevalucion.model';

describe('Component Tests', () => {
  describe('Autoevalucion Management Update Component', () => {
    let comp: AutoevalucionUpdateComponent;
    let fixture: ComponentFixture<AutoevalucionUpdateComponent>;
    let service: AutoevalucionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [AutoevalucionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AutoevalucionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AutoevalucionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AutoevalucionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Autoevalucion(123);
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
        const entity = new Autoevalucion();
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
