package software.xdev.saveactions.model;

import static java.util.stream.Collectors.toSet;
import static software.xdev.saveactions.model.ActionType.activation;
import static software.xdev.saveactions.model.ActionType.build;
import static software.xdev.saveactions.model.ActionType.global;
import static software.xdev.saveactions.model.ActionType.java;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;


@SuppressWarnings("java:S115")
public enum Action
{
	// Activation
	activate("启用保存时执行操作（保存每个文件前执行下方已配置的操作）",
		activation, true),
	
	activateOnShortcut("启用快捷键执行保存操作（默认“CTRL + SHIFT + S”）",
		activation, false),
	
	activateOnBatch("启用批量执行保存操作（“代码 > Save Actions > 在多个文件上执行”）",
		activation, false),
	
	noActionIfCompileErrors("文件存在编译错误时不执行操作（逐个文件判断）",
		activation, false),
	
	processAsync("异步处理文件（可减少界面卡顿，但依赖界面的处理器可能无法正常工作）",
		activation, false),
	
	// Global
	organizeImports("优化导入",
		global, true),
	
	reformat("重新格式化文件",
		global, true),
	
	reformatChangedCode("仅重新格式化已更改的代码（仅在配置版本控制时可用）",
		global, false),
	
	rearrange("重新排列字段和方法"
		+ "（在“文件 > 设置 > 编辑器 > 代码样式 > (...) > 排列”中配置）",
		global, false),
	
	// Build
	compile("[实验性] 编译文件（使用“构建 > 构建项目”）",
		build, false),
	
	reload("[实验性] 在运行中的调试器里重新加载文件"
		+ "（使用“运行 > 重新加载已更改的类”）",
		build, false),
	
	executeAction("[实验性] 执行操作"
		+ "（使用“文件 > 设置 > 外观与行为 > 快速列表”中的快速列表）",
		build, false),
	
	// Java fixes
	fieldCanBeFinal("为字段添加 final 修饰符",
		java, false),
	
	localCanBeFinal("为局部变量或参数添加 final 修饰符",
		java, false),
	
	localCanBeFinalExceptImplicit("为非隐式局部变量或参数添加 final 修饰符",
		java, false),
	
	methodMayBeStatic("为可静态化的方法添加 static 修饰符",
		java, false),
	
	unqualifiedFieldAccess("为字段访问添加 this 限定符",
		java, false),
	
	unqualifiedMethodAccess("为方法调用添加 this 限定符",
		java, false),
	
	unqualifiedStaticMemberAccess("为静态成员访问添加类限定符",
		java, false),
	
	customUnqualifiedStaticMemberAccess("为声明类之外的静态成员访问添加类限定符",
		java, false),
	
	missingOverrideAnnotation("添加缺失的 @Override 注解",
		java, false),
	
	useBlocks("为 if/while/for 语句添加代码块",
		java, false),
	
	generateSerialVersionUID("为 Serializable 类添加 serialVersionUID 字段",
		java, false),
	
	unnecessaryThis("删除字段和方法访问中不必要的 this 限定符",
		java, false),
	
	finalPrivateMethod("删除私有方法的 final 修饰符",
		java, false),
	
	unnecessaryFinalOnLocalVariableOrParameter("删除局部变量或参数不必要的 final 修饰符",
		java, false),
	
	explicitTypeCanBeDiamond("删除可由菱形操作符推断的显式泛型类型",
		java, false),
	
	unnecessarySemicolon("删除不必要的分号",
		java, false),
	
	singleStatementInBlock("删除 if/while/for 单语句代码块",
		java, false),
	
	accessCanBeTightened("降低字段或方法的访问级别",
		java, false);
	
	private final String text;
	private final ActionType type;
	private final boolean defaultValue;
	
	Action(final String text, final ActionType type, final boolean defaultValue)
	{
		this.text = text;
		this.type = type;
		this.defaultValue = defaultValue;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public ActionType getType()
	{
		return this.type;
	}
	
	public boolean isDefaultValue()
	{
		return this.defaultValue;
	}
	
	public static Set<Action> getDefaults()
	{
		return Arrays.stream(Action.values())
			.filter(Action::isDefaultValue)
			.collect(toSet());
	}
	
	public static Stream<Action> stream()
	{
		return Arrays.stream(values());
	}
	
	public static Stream<Action> stream(final ActionType type)
	{
		return Arrays.stream(values()).filter(action -> action.type.equals(type));
	}
}
