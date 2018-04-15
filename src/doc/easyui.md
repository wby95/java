# easyui
  
  ### ComboBox�������б��  ����һ�������п�ѡ�����һ�������п���ʾ��Ӧ����Ϣ
  ```
  <tr>
  	 <td>�������ࣺ</td>
  	 <td colspan="4">
  	 //valueField:��������ֵ���ư󶨵��������б��
  	 //textField::���������ֶ����ư󶨵��������б��
  	 //ͨ��URL����Զ���б����ݡ�
  	 	<input class="easyui-combobox" id="bName" name="product.bigType.id" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'productBigType_comboList.action'"/>
  	 </td>
  </tr>
  <tr>
  	 <td>����С�ࣺ</td>
  	 <td colspan="4">
  	 	<input class="easyui-combobox" id="sName" name="product.smallType.id" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'productSmallType_comboList.action'"/>
  	 </td>
  </tr>
  
  
  $(function(){
  		$("#bName").combobox({
  		//���û�ѡ���б����ʱ�򴥷���
  			onSelect:function(record){
  				$("#sName").combobox("reload","productSmallType_comboList.action?s_productSmallType.bigType.id="+record.id);
  				$("#sName").combobox("setValue","");
  			}
  		});
  	});
  	
  	
  	import java.util.List;
    
    import javax.annotation.Resource;
    
    import net.sf.json.JSONArray;
    import net.sf.json.JSONObject;
    import net.sf.json.JsonConfig;
    
    import org.apache.struts2.ServletActionContext;
    import org.springframework.stereotype.Controller;
    
    import com.java1234.entity.ProductBigType;
    import com.java1234.service.ProductBigTypeService;
    import com.java1234.util.ResponseUtil;
    import com.opensymphony.xwork2.ActionSupport;
    
    @Controller
    public class ProductBigTypeAction extends ActionSupport{
    
    	/**
    	 * 
    	 */
    	private static final long serialVersionUID = 1L;
    	
    	@Resource
    	private ProductBigTypeService productBigTypeService;
    	
    	public String comboList()throws Exception{
    		JSONArray jsonArray=new JSONArray();
    		JSONObject jsonObject=new JSONObject();
    		jsonObject.put("id", "");
    		jsonObject.put("name", "��ѡ��...");
    		jsonArray.add(jsonObject);
    		List<ProductBigType> productBigTypeList=productBigTypeService.findAllBigTypeList();
    		JsonConfig jsonConfig=new JsonConfig();
    		jsonConfig.setExcludes(new String[]{"productList","smallTypeList"});
    		JSONArray rows=JSONArray.fromObject(productBigTypeList, jsonConfig);
    		jsonArray.addAll(rows);
    		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
    		return null;
    	}
    
    }

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.java1234.entity.ProductSmallType;
import com.java1234.service.ProductSmallTypeService;
import com.java1234.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ProductSmallTypeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductSmallType s_productSmallType;

	public ProductSmallType getS_productSmallType() {
		return s_productSmallType;
	}

	public void setS_productSmallType(ProductSmallType s_productSmallType) {
		this.s_productSmallType = s_productSmallType;
	}

	@Resource
	private ProductSmallTypeService productSmallTypeService;
	
	public String comboList()throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "��ѡ��...");
		jsonArray.add(jsonObject);
		List<ProductSmallType> productSmallTypeList=productSmallTypeService.findProductSmallTypeList(s_productSmallType);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"bigType","productList"});
		JSONArray rows=JSONArray.fromObject(productSmallTypeList, jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}

}

  	 
  
  ```
  ### easyui��ҳ��һ��Ĳ���
  
  ```
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">



// ���һ��δѡ��״̬��ѡ����
$('#tt').tabs('add',{
	title: '��ѡ����',
	selected: false
	//...
});




	function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/"+url+"'></iframe>";
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
	<table style="padding: 5px" width="100%">
		<tr>
			<td width="50%">
				<img src="${pageContext.request.contextPath}/images/bglogo.png"/>
			</td>
			<td valign="bottom" align="right" width="50%">
				<font size="3">&nbsp;&nbsp;<strong>��ӭ��</strong>${currentUser.userName }</font>
			</td>
		</tr>
	</table>
</div>
<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="��ҳ" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px"><font color="red" size="10">��ӭʹ����������̨����ϵͳ</font></div>
		</div>
	</div>
</div>
<div region="west" style="width: 200px" title="�����˵�" split="true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="�û�����" data-options="selected:true,iconCls:'icon-user'" style="padding: 10px">
			<a href="javascript:openTab('�û�����','userManage.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">�����û�</a>
		</div>
		
		<div title="��Ʒ����"  data-options="iconCls:'icon-product'" style="padding:10px;">
			<a href="javascript:openTab('��Ʒ����','productManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">������Ʒ</a>
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">������Ʒ����</a>
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">������ƷС��</a>
		</div>
		<div title="��������"  data-options="iconCls:'icon-order'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">������</a>
		</div>
		<div title="���Թ���" data-options="iconCls:'icon-comment'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">��������</a>
		</div>
		<div title="�������"  data-options="iconCls:'icon-notice'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">������</a>
		</div>
		<div title="���Ź���"  data-options="iconCls:'icon-news'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">��������</a>
		</div>
		<div title="��ǩ����"  data-options="iconCls:'icon-tag'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">�����ǩ</a>
		</div>
		<div title="ϵͳ����"  data-options="iconCls:'icon-item'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">�޸�����</a>
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">��ȫ�˳�</a>
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">ˢ��ϵͳ����</a>
		</div>
	</div>
</div>
<div region="south" style="height: 25px;padding: 5px;" align="center">

</div>
</body>
</html>



```

### CRUD
```

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    
    
    //ģ����ѯ
	var url;
	function searchUser(){
		$("#dg").datagrid('load',{
		//ʹ��һЩ������ѯ���ݡ�
			"s_user.userName":$("#s_userName").val()
		});
	}
	//ɾ��
	function deleteUser(){
	    //�������б�ѡ�е��У���û�м�¼��ѡ�е�ʱ�򽫷���һ�������顣
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("ϵͳ��ʾ","��ѡ��Ҫɾ�������ݣ�");
			return;
		}
		var strIds=[];
		//��������е�id��һ������
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		//������id�ö��Ÿ���
		var ids=strIds.join(",");
		$.messager.confirm("ϵͳ��ʾ","��ȷ��Ҫɾ����<font color=red>"+selectedRows.length+"</font>��������",function(r){
			if(r){
				$.post("user_deleteUser.action",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("ϵͳ��ʾ","�����ѳɹ�ɾ����");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("ϵͳ��ʾ","����ɾ��ʧ�ܣ�");
					}
					//"json" ʹ��resultΪһ��object����
				},"json");
			}
		});
		
	}
	
	//���
	function openUserAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","����û���Ϣ");
		url="user_saveUser.action";
	}
	
	
	function saveUser(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				if($("#sex").combobox("getValue")==""){
					$.messager.alert("ϵͳ��ʾ","��ѡ���Ա�");
					return false;
				}
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("ϵͳ��ʾ","����ɹ�");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("ϵͳ��ʾ","����ʧ��");
					return;
				}
			}
		});
	}
	//�޸�
	function openUserModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("ϵͳ��ʾ","��ѡ��һ��Ҫ�༭�����ݣ�");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","�༭�û���Ϣ");
		$("#trueName").val(row.trueName);
		$("#userName").val(row.userName);
		$("#password").val(row.password);
		$("#sex").combobox("setValue",row.sex);
		$("#birthday").datebox("setValue",row.birthday);
		$("#dentityCode").val(row.dentityCode);
		$("#email").val(row.email);
		$("#mobile").val(row.mobile);
		$("#address").val(row.address);
		url="user_saveUser.action?user.id="+row.id;
	}
	
	function resetValue(){
		$("#trueName").val("");
		$("#userName").val("");
		$("#password").val("");
		$("#sex").combobox("setValue","");
		$("#birthday").datebox("setValue","");
		$("#dentityCode").val("");
		$("#email").val("");
		$("#mobile").val("");
		$("#address").val("");
	}
	
	function closeUserDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
</script>
</head>
<body style="margin:1px;">
	<table id="dg" title="�û�����" class="easyui-datagrid"
	 fitColumns="true" pagination="true" rownumbers="true"
	 url="user_list.action" fit="true" toolbar="#tb">
	 <thead>
	 	<tr>
	 		<th field="cb" checkbox="true" align="center"></th>
	 		<th field="id" width="50" align="center">���</th>
	 		<th field="trueName" width="100" align="center">��ʵ����</th>
	 		<th field="userName" width="100" align="center">�û���</th>
	 		<th field="password" width="100" align="center">����</th>
	 		<th field="sex" width="50" align="center">�Ա�</th>
	 		<th field="birthday" width="100" align="center">��������</th>
	 		<th field="dentityCode" width="150" align="center">���֤</th>
	 		<th field="email" width="100" align="center">�ʼ�</th>
	 		<th field="mobile" width="100" align="center">��ϵ�绰</th>
	 		<th field="address" width="100" align="center">�ջ���ַ</th>
	 	</tr>
	 </thead>
	</table>
	<div id="tb">
		<div>
			<a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">���</a>
			<a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">�޸�</a>
			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">ɾ��</a>
		</div>
		<div>
			&nbsp;�û�����&nbsp;<input type="text" id="s_userName" size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
			<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">����</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 570px;height:300px;padding: 10px 20px"
	  closed="true" buttons="#dlg-buttons">
	 	<form id="fm" method="post">
	 		<table cellspacing="8px">
	 			<tr>
	 				<td>��ʵ������</td>
	 				<td><input type="text" id="trueName" name="user.trueName" class="easyui-validatebox" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>�û�����</td>
	 				<td><input type="text" id="userName" name="user.userName" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>���룺</td>
	 				<td><input type="text" id="password" name="user.password" class="easyui-validatebox" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>�Ա�</td>
	 				<td>
	 					<select class="easyui-combobox" id="sex" name="user.sex" style="width: 154px;" editable="false" panelHeight="auto">
	 						<option value="">��ѡ���Ա�</option>
	 						<option value="��">��</option>
	 						<option value="Ů">Ů</option>
	 					</select>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>�������ڣ�</td>
	 				<td><input type="text" id="birthday" name="user.birthday" class="easyui-datebox" editable="false" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>���֤��</td>
	 				<td><input type="text" id="dentityCode" name="user.dentityCode" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>�ʼ���</td>
	 				<td><input type="text" id="email" name="user.email" class="easyui-validatebox" validType="email" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>��ϵ�绰��</td>
	 				<td><input type="text" id="mobile" name="user.mobile" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>�ջ���ַ��</td>
	 				<td colspan="4">
	 					<input type="text" id="address" name="user.address" class="easyui-validatebox" required="true" style="width: 350px;"/>
	 				</td>
	 			</tr>
	 		</table>
	 	</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">����</a>
		<a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">�ر�</a>
	</div>
</body>
</html>
```