import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { VerificadorUpdateComponent } from 'app/entities/verificador/verificador-update.component';
import { VerificadorService } from 'app/entities/verificador/verificador.service';
import { Verificador } from 'app/shared/model/verificador.model';

describe('Component Tests', () => {
  describe('Verificador Management Update Component', () => {
    let comp: VerificadorUpdateComponent;
    let fixture: ComponentFixture<VerificadorUpdateComponent>;
    let service: VerificadorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [VerificadorUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VerificadorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VerificadorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VerificadorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Verificador(123);
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
        const entity = new Verificador();
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
