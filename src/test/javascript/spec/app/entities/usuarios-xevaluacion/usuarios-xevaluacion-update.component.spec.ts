import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { UsuariosXevaluacionUpdateComponent } from 'app/entities/usuarios-xevaluacion/usuarios-xevaluacion-update.component';
import { UsuariosXevaluacionService } from 'app/entities/usuarios-xevaluacion/usuarios-xevaluacion.service';
import { UsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';

describe('Component Tests', () => {
  describe('UsuariosXevaluacion Management Update Component', () => {
    let comp: UsuariosXevaluacionUpdateComponent;
    let fixture: ComponentFixture<UsuariosXevaluacionUpdateComponent>;
    let service: UsuariosXevaluacionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [UsuariosXevaluacionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UsuariosXevaluacionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UsuariosXevaluacionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UsuariosXevaluacionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UsuariosXevaluacion(123);
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
        const entity = new UsuariosXevaluacion();
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
