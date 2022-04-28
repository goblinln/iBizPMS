import { AppGlobalService } from '../app-service';
import { AppDEUIAction } from './app-ui-action';

export class AppSysAction extends AppDEUIAction {

  /**
   * 初始化AppSysAction
   *
   * @memberof AppSysAction
   */
  constructor(opts: any, context?: any) {
      super(opts, context);
  }

  /**
   * 执行界面行为
   *
   * @param args
   * @param context
   * @param params
   * @param $event
   * @param xData
   * @param actionContext
   * @param srfParentDeName
   *
   * @memberof AppSysAction
   */
  public async execute(
      args: any[],
      context: any = {},
      params: any = {},
      $event?: any,
      xData?: any,
      actionContext?: any,
      srfParentDeName?: string,
      deUIService?: any,
  ) {
      (AppGlobalService.getInstance() as any).executeGlobalAction(
        this.actionModel.uIActionTag,
        args,
        context,
        params,
        $event,
        xData,
        actionContext,
        srfParentDeName,
      );
    }

}
