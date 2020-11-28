import { IVerificador } from 'app/shared/model/verificador.model';
import { IProceso } from 'app/shared/model/proceso.model';

export interface ISubproceso {
  id?: number;
  descripcion?: string;
  verificadors?: IVerificador[];
  proceso?: IProceso;
}

export class Subproceso implements ISubproceso {
  constructor(public id?: number, public descripcion?: string, public verificadors?: IVerificador[], public proceso?: IProceso) {}
}
