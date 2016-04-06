package com.beiyanght.csms.common.constants;

/**
 * 功能名	系统-共通
 * 类描述	通用业务常量
 * @author  郭董宁
 */
public final class CommonBusinessNames {
	/*----------------业务编号流程START---------------*/
	// 流程名称
    public static final String BUSINESS_FLOW = "业务编号流程";
	// 状态
    public static final String BUSINESS_STATUS_UPDATE = "新增";
    public static final String BUSINESS_STATUS_SUBMIT = "提交";
    public static final String BUSINESS_STATUS_CLOSE = "结案";
	// 动作
    public static final String BUSINESS_ACTION_UPDATE = "修改";
    public static final String BUSINESS_ACTION_SUBMIT = "提交";
    public static final String BUSINESS_ACTION_CLOSE = "结案";
    public static final String BUSINESS_ACTION_ANTICLOSE = "反结案";
	/*----------------业务编号流程END---------------*/
    
	/*----------------预约单流程START---------------*/
	// 流程名称
    public static final String BOOKING_FLOW = "预约单操作流程";
	// 状态

	// 动作
    public static final String BOOKING_ACTION_UPDATE = "修改";
    public static final String BOOKING_ACTION_SUBMIT = "提交";
    public static final String BOOKING_ACTION_CLOSE = "结案";
    public static final String BOOKING_ACTION_UNCLOSE = "反结案";
    public static final String BOOKING_ACTION_DROP = "作废";
    public static final String BOOKING_ACTION_UNDROP = "恢复";
	/*----------------预约单流程END---------------*/
    
	/*----------------（运输）作业单流程START---------------*/
	// 状态
	public static final String TRANSPORT_WORKING_STATUS_SUBMIT = "提交";
	public static final String TRANSPORT_WORKING_STATUS_CLOSE = "结案";
	public static final String TRANSPORT_WORKING_STATUS_CLEARSTORE = "清库待审";
	public static final String TRANSPORT_WORKING_STATUS_UNPASS = "不通过";
	public static final String TRANSPORT_WORKING_STATUS_DROP = "作废";
	// 动作
    public static final String TRANSPORT_WORKING_ACTION_UPDATE = "修改";
    public static final String TRANSPORT_WORKING_ACTION_NORMAL_SUBMIT = "正常提交";
    public static final String TRANSPORT_WORKING_ACTION_UNENOUGH_SUBMIT = "不足提交";
    public static final String TRANSPORT_WORKING_ACTION_CLOSE = "结案";
    public static final String TRANSPORT_WORKING_ACTION_UNCLOSE = "反结案";
    public static final String TRANSPORT_WORKING_ACTION_DROP = "作废";
    public static final String TRANSPORT_WORKING_ACTION_UNDROP = "恢复";
    public static final String TRANSPORT_WORKING_ACTION_PASS = "通过";
    public static final String TRANSPORT_WORKING_ACTION_UNPASS = "不通过";
	/*----------------（运输）作业单流程END---------------*/
	
	/*----------------移库作业单流程START---------------*/
	// 动作
    public static final String MOVE_WORKING_ACTION_UPDATE = "修改";
    public static final String MOVE_WORKING_ACTION_SUBMIT = "提交";
    public static final String MOVE_WORKING_ACTION_CLOSE = "结案";
    public static final String MOVE_WORKING_ACTION_UNCLOSE = "反结案";
    public static final String MOVE_WORKING_ACTION_DROP = "作废";
    public static final String MOVE_WORKING_ACTION_UNDROP = "恢复";
	/*----------------移库作业单流程END---------------*/

	/*----------------移库单流程START---------------*/
	// 动作
    public static final String MOVE_WAREHOUSE_ACTION_UPDATE = "修改";
    public static final String MOVE_WAREHOUSE_ACTION_SUBMIT = "提交";
    public static final String MOVE_WAREHOUSE_ACTION_PASS = "通过";
    public static final String MOVE_WAREHOUSE_ACTION_UNPASS = "不通过";
	/*----------------移库单流程END---------------*/
    
	/*----------------作业计划流程START---------------*/
	// 动作
    public static final String WORKING_PLAN_ACTION_OPERATE_UPDATE = "操作修改";
    public static final String WORKING_PLAN_ACTION_TALLY_UPDATE = "理货修改";
    public static final String WORKING_PLAN_ACTION_OPERATE_SUBMIT = "操作提交";
    public static final String WORKING_PLAN_ACTION_TALLY_SUBMIT = "理货提交";
    public static final String WORKING_PLAN_ACTION_DROP = "作废";
    public static final String WORKING_PLAN_ACTION_UNDROP = "恢复";
    public static final String WORKING_PLAN_ACTION_SEND_BACK = "驳回";
	/*----------------作业计划流程END---------------*/
	
    /*----------------入库计划流程 START---------------*/
    // 流程名称
    public static final String INBOUND_PLAN_FLOW = "入库计划管理流程";
    // 状态
    public static final String INBOUND_PLAN_STATUS_NEW = "开始";
    public static final String INBOUND_PLAN_STATUS_SUBMIT = "提交";
    public static final String INBOUND_PLAN_STATUS_CHANGE = "修改";
    public static final String INBOUND_PLAN_STATUS_DROP = "作废";
    public static final String INBOUND_PLAN_STATUS_CLOSE = "结案";
    // 动作
    public static final String INBOUND_PLAN_ACTION_START = "开始";
    public static final String INBOUND_PLAN_ACTION_SUBMIT = "提交";
    public static final String INBOUND_PLAN_ACTION_CHANGE = "修改";
    public static final String INBOUND_PLAN_ACTION_DROP = "作废";
    public static final String INBOUND_PLAN_ACTION_RECOVER = "恢复";
    public static final String INBOUND_PLAN_ACTION_CLOSE = "结案";
    public static final String INBOUND_PLAN_ACTION_ANTI_CLOSE = "反结案";
    /*----------------入库计划流程 END---------------*/
    
	/*----------------入库单流程START---------------*/
	// 动作
    public static final String INBOUND_BILL_ACTION_UPDATE = "修改";
    public static final String INBOUND_BILL_ACTION_SUBMIT = "提交";
    public static final String INBOUND_BILL_ACTION_PASS = "通过";
    public static final String INBOUND_BILL_ACTION_UNPASS = "不通过";
	/*----------------入库单流程END---------------*/
	
	/*----------------出库单流程START---------------*/
	// 动作
    public static final String OUTBOUND_BILL_ACTION_NEW = "新增";
    public static final String OUTBOUND_BILL_ACTION_UPDATE = "修改";
    public static final String OUTBOUND_BILL_ACTION_SUBMIT = "提交";
    public static final String OUTBOUND_BILL_ACTION_PASS = "通过";
    public static final String OUTBOUND_BILL_ACTION_UNPASS = "不通过";
    // 状态
    public static final String OUTBOUND_BILL_STATUS_NEW = "新增";
    public static final String OUTBOUND_BILL_STATUS_UPDATE = "修改";
    public static final String OUTBOUND_BILL_STATUS_SUBMIT = "提交";
    public static final String OUTBOUND_BILL_STATUS_PASS = "通过";
    public static final String OUTBOUND_BILL_STATUS_UNPASS = "不通过";
	/*----------------出库单流程END---------------*/
    
    /*----------------装卸单操作流程 START---------------*/
    // 流程名称
    public static final String LOAD_UNLOAD_FLOW = "装卸单操作流程";
    // 状态
    public static final String LOAD_UNLOAD_STATUS_NEW = "开始";
    public static final String LOAD_UNLOAD_STATUS_SUBMIT = "提交";
    public static final String LOAD_UNLOAD_STATUS_CHANGE = "修改";
    public static final String LOAD_UNLOAD_STATUS_PASS = "通过";
    // 动作
    public static final String LOAD_UNLOAD_ACTION_START = "开始";
    public static final String LOAD_UNLOAD_ACTION_SUBMIT = "提交";
    public static final String LOAD_UNLOAD_ACTION_CHANGE = "修改";
    public static final String LOAD_UNLOAD_ACTION_PASS = "通过";
    public static final String LOAD_UNLOAD_ACTION_UNPASS = "不通过";
    /*----------------装卸单操作流程 END---------------*/
    
    /*----------------出库计划流程 START---------------*/
    // 流程名称
    public static final String OUTBOUND_PLAN_FLOW = "出库计划管理流程";
    // 状态
    public static final String OUTBOUND_PLAN_STATUS_NEW = "开始";
    public static final String OUTBOUND_PLAN_STATUS_SUBMIT = "提交";
    public static final String OUTBOUND_PLAN_STATUS_CHANGE = "修改";
    public static final String OUTBOUND_PLAN_STATUS_DROP = "作废";
    public static final String OUTBOUND_PLAN_STATUS_CLOSE = "结案";
    // 动作
    public static final String OUTBOUND_PLAN_ACTION_START = "开始";
    public static final String OUTBOUND_PLAN_ACTION_SUBMIT = "提交";
    public static final String OUTBOUND_PLAN_ACTION_CHANGE = "修改";
    public static final String OUTBOUND_PLAN_ACTION_DROP = "作废";
    public static final String OUTBOUND_PLAN_ACTION_RECOVER = "恢复";
    public static final String OUTBOUND_PLAN_ACTION_CLOSE = "结案";
    public static final String OUTBOUND_PLAN_ACTION_ANTI_CLOSE = "反结案";
    /*----------------出库计划流程 END---------------*/
    
    /*----------------移库计划流程 START---------------*/
    // 流程名称
    public static final String MOVEBOUND_PLAN_FLOW = "移库计划管理流程";
    // 状态
    public static final String MOVEBOUND_PLAN_STATUS_NEW = "新增";
    public static final String MOVEBOUND_PLAN_STATUS_SUBMIT = "提交";
    public static final String MOVEBOUND_PLAN_STATUS_CHANGE = "修改";
    public static final String MOVEBOUND_PLAN_STATUS_DORP = "作废";
    public static final String MOVEBOUND_PLAN_STATUS_CLOSE = "结案";
    // 动作
    public static final String MOVEBOUND_PLAN_ACTION_START = "开始";
    public static final String MOVEBOUND_PLAN_ACTION_SUBMIT = "提交";
    public static final String MOVEBOUND_PLAN_ACTION_CHANGE = "修改";
    public static final String MOVEBOUND_PLAN_ACTION_DROP = "作废";
    public static final String MOVEBOUND_PLAN_ACTION_RECOVER = "恢复";
    public static final String MOVEBOUND_PLAN_ACTION_CLOSE = "结案";
    public static final String MOVEBOUND_PLAN_ACTION_ANTI_CLOSE = "反结案";
    /*----------------移库计划流程 END---------------*/
    
    /*----------------调整单流程START---------------*/
    // 动作
    public static final String ADJUST_BILL_ACTION_UPDATE = "修改";
    public static final String ADJUST_BILL_ACTION_SUBMIT = "提交";
    public static final String ADJUST_BILL_ACTION_UNPASS = "不通过";
    public static final String ADJUST_BILL_ACTION_PASS = "通过";
    // 状态
    public static final String ADJUST_BILL_STATUS_NEW = "新增";
    public static final String ADJUST_BILL_STATUS_UPDATE = "修改";
    public static final String ADJUST_BILL_STATUS_SUBMIT = "提交";
    public static final String ADJUST_BILL_STATUS_UNPASS = "不通过";
    public static final String ADJUST_BILL_STATUS_PASS = "通过";
    
    /*----------------调整单流程END---------------*/
	
	/*----------------衡重任务流程START---------------*/
	// 动作
    public static final String WEIGHT_TASK_ACTION_UPDATE = "修改";
    public static final String WEIGHT_TASK_ACTION_CLOSE = "结案";
    public static final String WEIGHT_TASK_ACTION_UNCLOSE = "反结案";
	/*----------------衡重任务流程END---------------*/
    
    /*----------------客户类型 START---------------*/
    public static final String CLIENT_TYPE_SELF = "自身";
    public static final String CLIENT_TYPE_OWNER = "货主";
    public static final String CLIENT_TYPE_SHIP = "船公司";
    public static final String CLIENT_TYPE_AGENCY = "船代";
    /*----------------客户类型 END---------------*/
    
    /*----------------合同类型 START---------------*/
    public static final String CONTRACT_TYPE_SHIP = "船方";
    public static final String CONTRACT_TYPE_OWNER = "货方";
    public static final String CONTRACT_TYPE_HIRE = "租赁";
    public static final String CONTRACT_TYPE_WEIGHT = "过磅";
    public static final String CONTRACT_TYPE_DISMANTLE = "拆装";
    /*----------------客户类型 END---------------*/
    
    /*----------------结算单类型 START---------------*/
    public static final String SETTLEMENT_TYPE_SHIP = "船方";
    public static final String SETTLEMENT_TYPE_OWNER = "货方";
    public static final String SETTLEMENT_TYPE_HIRE = "租赁";
    public static final String SETTLEMENT_TYPE_WEIGHT = "过磅";
    public static final String SETTLEMENT_TYPE_DISMANTLE = "拆装";
    /*----------------结算单类型 END---------------*/
    
    /*----------------合同管理流程 START---------------*/
    // 流程名称
    public static final String CONTRACT_FLOW = "合同管理流程";
    // 状态
    public static final String CONTRACT_STATUS_START = "开始";
    public static final String CONTRACT_STATUS_NEW = "新增";
    public static final String CONTRACT_STATUS_SUBMIT = "提交";
    public static final String CONTRACT_STATUS_CHANGE = "修改";
    public static final String CONTRACT_STATUS_PASS = "通过";
    public static final String CONTRACT_STATUS_UNPASS = "不通过";
    public static final String CONTRACT_STATUS_DROP = "作废";
    // 动作
    public static final String CONTRACT_ACTION_START = "开始";
    public static final String CONTRACT_ACTION_SUBMIT = "提交";
    public static final String CONTRACT_ACTION_CHANGE = "修改";
    public static final String CONTRACT_ACTION_PASS = "通过";
    public static final String CONTRACT_ACTION_UNPASS = "不通过";
    public static final String CONTRACT_ACTION_DROP = "作废";
    public static final String CONTRACT_ACTION_RECOVER = "恢复";
    /*----------------合同管理流程类型 END---------------*/
    
    /*----------------单据结算状态 START---------------*/
    // 状态
    public static final Integer SETTLEMENT_UNCALC = 0;
    public static final Integer SETTLEMENT_CALC = 1;
    public static final Integer SETTLEMENT_SUBMIT = 2;
    public static final Integer SETTLEMENT_CONFIRM = 3;
    public static final Integer SETTLEMENT_CLOSE = 4;

    /*----------------单据结算状态 END---------------*/
    
    /*----------------结算管理流程 START---------------*/

    // 流程名称
    public static final String SETTLEMENT_FLOW = "结算管理流程";
    // 状态
    public static final String SETTLEMENT_STATUS_START = "开始";
    public static final String SETTLEMENT_STATUS_UNCALC = "未结算";
    public static final String SETTLEMENT_STATUS_CALC = "结算";
    public static final String SETTLEMENT_STATUS_SUBMIT = "提交";
    public static final String SETTLEMENT_STATUS_CONFIRM = "确认";
    public static final String SETTLEMENT_STATUS_CLOSE = "结案";
    // 动作
    public static final String SETTLEMENT_ACTION_START = "开始";
    public static final String SETTLEMENT_ACTION_SUBMIT = "提交";
    public static final String SETTLEMENT_ACTION_CHANGE = "修改";
    public static final String SETTLEMENT_ACTION_CONFIRM = "确认";
    public static final String SETTLEMENT_ACTION_ANTICONFIRM = "反确认";
    public static final String SETTLEMENT_ACTION_CLOSE = "结案";
    public static final String SETTLEMENT_ACTION_ANTICLOSE = "反结案";
    /*----------------结算管理流程类型 END---------------*/
    
    /*----------------支持自动结算的费目 START---------------*/
    public static final String AUTO_ALONGSIDE = "靠泊费";
    public static final String AUTO_ATTACH = "解系缆费";
    /*----------------支持自动结算的费目 END---------------*/
    
    /*----------------支持自动结算的费用要素 START---------------*/
    // 船方
    public static final String ELEMENT_STOP_DAYS = "天数";
    public static final String ELEMENT_SHIP_WEIGHT = "吨位";
    public static final String ELEMENT_ATTACH_TIMES = "次数";
    
    // 货方
    public static final String ELEMENT_WORK_TYPE = "作业单类型";
    public static final String ELEMENT_FOR_PORT = "集疏港";
    public static final String ELEMENT_PAY_OR_NOT = "收费与否";
    public static final String ELEMENT_LOAD_WEIGHT = "装卸吨";
    public static final String ELEMENT_PAY_TYPE = "计费类型";
    
    public static final String ELEMENT_STORE_WEIGHT = "仓储吨";
    public static final String ELEMENT_STORE_DAYS = "天数";
    public static final String ELEMENT_STORE_TYPE  = "仓储类型";
    
    // 租赁
    public static final String ELEMENT_HIRE_DAYS  = "天数";
    
    // 拆装箱
    public static final String ELEMENT_DISMANTLE_TYPE  = "箱型";
    public static final String ELEMENT_DISMANTLE_SIZE  = "尺寸";
    public static final String ELEMENT_DISMANTLE_TON  = "计费吨";
    public static final String ELEMENT_DISMANTLE_NUM  = "箱量";
    
    // 过磅
    public static final String ELEMENT_WEIGHT_TON = "过磅吨";
    public static final String ELEMENT_COST_TYPE = "计费方式";
    public static final String ELEMENT_SUM_COST_TYPE = "总重计费类型";
    public static final String ELEMENT_EACH_COST_TYPE = "单车计费类型";
    public static final String ELEMENT_WEIGHT_TYPE = "称重方式";
    /*----------------支持自动结算的费用要素 END---------------*/
    
    /*----------------租赁合同流程 START---------------*/
    // 流程名称
    public static final String HIRE_CONTRACT_FLOW = "租赁合同流程";
    // 状态
    public static final String HIRE_STATUS_START = "开始";
    public static final String HIRE_STATUS_NEW = "新增";
    public static final String HIRE_STATUS_SUBMIT = "提交";
    public static final String HIRE_STATUS_CHANGE = "修改";
    public static final String HIRE_STATUS_PASS = "通过";
    public static final String HIRE_STATUS_UNPASS = "不通过";
    public static final String HIRE_STATUS_DROP = "作废";
    // 动作
    public static final String HIRE_ACTION_START = "开始";
    public static final String HIRE_ACTION_SUBMIT = "提交";
    public static final String HIRE_ACTION_CHANGE = "修改";
    public static final String HIRE_ACTION_PASS = "通过";
    public static final String HIRE_ACTION_UNPASS = "不通过";
    public static final String HIRE_ACTION_DROP = "作废";
    public static final String HIRE_ACTION_RECOVER = "恢复";
    /*----------------租赁合同流程 END---------------*/
    
    /*----------------仓储中间结算管理流程 START---------------*/
    // 流程名称
    public static final String INTERIM_MANAGE_FLOW = "仓储中间结算管理流程";
    // 状态
    public static final String INTERIM_STATUS_START = "开始";
    public static final String INTERIM_STATUS_NEW = "新增";
    public static final String INTERIM_STATUS_SUBMIT = "提交";
    public static final String INTERIM_STATUS_CHANGE = "修改";
    public static final String INTERIM_STATUS_PASS = "通过";
    public static final String INTERIM_STATUS_UNPASS = "不通过";
    public static final String INTERIM_STATUS_DROP = "作废";
    // 动作
    public static final String INTERIM_ACTION_START = "开始";
    public static final String INTERIM_ACTION_SUBMIT = "提交";
    public static final String INTERIM_ACTION_CHANGE = "修改";
    public static final String INTERIM_ACTION_PASS = "通过";
    public static final String INTERIM_ACTION_UNPASS = "不通过";
    public static final String INTERIM_ACTION_DROP = "作废";
    public static final String INTERIM_ACTION_RECOVER = "恢复";
    /*----------------租赁合同流程 END---------------*/
    /*----------------货权转移流程 START---------------*/
    // 流程名称
    public static final String TITLE_TRANSFER_FLOW = "货权转移流程流程";
    // 状态
    public static final String TITLE_TRANSFER_START = "开始";
    public static final String TITLE_TRANSFER_NEW = "新增";
    public static final String TITLE_TRANSFER_SUBMIT = "提交";
    public static final String TITLE_TRANSFER_CHANGE = "修改";
    public static final String TITLE_TRANSFER_PASS = "通过";
    public static final String TITLE_TRANSFER_UNPASS = "不通过";
    /*----------------货权转移流程 END---------------*/
    
    /*----------------拆装箱作业单流程 START---------------*/
    // 流程名称
    public static final String VANNING_DEVANNING_FLOW = "拆装箱作业单流程";
    // 动作
    public static final String VANNING_DEVANNING_ACTION_UPDATE = "修改";
    public static final String VANNING_DEVANNING_ACTION_SUBMIT = "提交";
    public static final String VANNING_DEVANNING_ACTION_CLOSE = "结案";
    public static final String VANNING_DEVANNING_ACTION_UNCLOSE = "反结案";
    public static final String VANNING_DEVANNING_ACTION_DROP = "作废";
    public static final String VANNING_DEVANNING_ACTION_UNDROP = "恢复";
    // 状态
    public static final String VANNING_DEVANNING_STATUS_NEW = "新增";
    public static final String VANNING_DEVANNING_STATUS_SUBMIT = "提交";
    public static final String VANNING_DEVANNING_STATUS_CLOSE = "结案";
    /*----------------拆装箱作业单流程 END---------------*/
}
