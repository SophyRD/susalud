import { ISubproceso } from 'app/shared/model/subproceso.model';
import { IVerificador } from 'app/shared/model/verificador.model';
import { IMacroproceso } from 'app/shared/model/macroproceso.model';

export interface IProceso {
  id?: number;
  descripcion?: string;
  subprocesos?: ISubproceso[];
  verificadors?: IVerificador[];
  macroproceso?: IMacroproceso;
}

export class Proceso implements IProceso {
  constructor(
    public id?: number,
    public descripcion?: string,
    public subprocesos?: ISubproceso[],
    public verificadors?: IVerificador[],
    public macroproceso?: IMacroproceso
  ) {}
}
