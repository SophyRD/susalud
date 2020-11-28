import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { SubprocesoUpdateComponent } from 'app/entities/subproceso/subproceso-update.component';
import { SubprocesoService } from 'app/entities/subproceso/subproceso.service';
import { Subproceso } from 'app/shared/model/subproceso.model';

describe('Component Tests', () => {
  describe('Subproceso Management Update Component', () => {
    let comp: SubprocesoUpdateComponent;
    let fixture: ComponentFixture<SubprocesoUpdateComponent>;
    let service: SubprocesoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [SubprocesoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SubprocesoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubprocesoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubprocesoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Subproceso(123);
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
        const entity = new Subproceso();
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
