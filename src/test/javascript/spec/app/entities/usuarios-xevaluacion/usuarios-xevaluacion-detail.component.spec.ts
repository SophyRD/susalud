import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { UsuariosXevaluacionDetailComponent } from 'app/entities/usuarios-xevaluacion/usuarios-xevaluacion-detail.component';
import { UsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';

describe('Component Tests', () => {
  describe('UsuariosXevaluacion Management Detail Component', () => {
    let comp: UsuariosXevaluacionDetailComponent;
    let fixture: ComponentFixture<UsuariosXevaluacionDetailComponent>;
    const route = ({ data: of({ usuariosXevaluacion: new UsuariosXevaluacion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [UsuariosXevaluacionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UsuariosXevaluacionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UsuariosXevaluacionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load usuariosXevaluacion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.usuariosXevaluacion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
