import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProyectoTestModule } from '../../../test.module';
import { VerificadorDetailComponent } from 'app/entities/verificador/verificador-detail.component';
import { Verificador } from 'app/shared/model/verificador.model';

describe('Component Tests', () => {
  describe('Verificador Management Detail Component', () => {
    let comp: VerificadorDetailComponent;
    let fixture: ComponentFixture<VerificadorDetailComponent>;
    const route = ({ data: of({ verificador: new Verificador(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProyectoTestModule],
        declarations: [VerificadorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VerificadorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VerificadorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load verificador on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.verificador).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
