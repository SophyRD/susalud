import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { MacroprocesoUpdateComponent } from 'app/entities/macroproceso/macroproceso-update.component';
import { MacroprocesoService } from 'app/entities/macroproceso/macroproceso.service';
import { Macroproceso } from 'app/shared/model/macroproceso.model';

describe('Component Tests', () => {
  describe('Macroproceso Management Update Component', () => {
    let comp: MacroprocesoUpdateComponent;
    let fixture: ComponentFixture<MacroprocesoUpdateComponent>;
    let service: MacroprocesoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [MacroprocesoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MacroprocesoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MacroprocesoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MacroprocesoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Macroproceso(123);
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
        const entity = new Macroproceso();
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
