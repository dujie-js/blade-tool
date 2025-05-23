/**
 * Copyright (c) 2018-2099, Chill Zhuang 庄骞 (bladejava@qq.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.core.swagger;

import net.dreamlu.mica.auto.annotation.AutoService;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.launch.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.Ordered;

import java.util.Properties;

/**
 * 初始化Swagger配置
 *
 * @author Chill
 */
@AutoService(LauncherService.class)
public class SwaggerLauncherServiceImpl implements LauncherService {
	@Override
	public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
		Properties props = System.getProperties();
		if (profile.equals(AppConstant.PROD_CODE)) {
			props.setProperty("swagger.enabled", "false");
			props.setProperty("knife4j.enable", "false");
			props.setProperty("knife4j.production", "true");
			props.setProperty("springdoc.api-docs.enabled", "false");
			props.setProperty("springdoc.api-usage.enabled", "false");
			props.setProperty("springdoc.swagger-ui.enabled", "false");
		} else {
			props.setProperty("swagger.enabled", "true");
			props.setProperty("knife4j.enable", "true");
			props.setProperty("knife4j.production", "false");
			props.setProperty("springdoc.api-docs.enabled", "true");
			props.setProperty("springdoc.api-usage.enabled", "true");
			props.setProperty("springdoc.swagger-ui.enabled", "true");
			props.setProperty("spring.mvc.pathmatch.matching-strategy", "ANT_PATH_MATCHER");
		}
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
