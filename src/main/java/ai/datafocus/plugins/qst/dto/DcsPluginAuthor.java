package ai.datafocus.plugins.qst.dto;

import java.util.List;

import lombok.Data;

@Data
public class DcsPluginAuthor {
  private Integer id;
  private String name;
  private String pass_hash;
  private boolean enabled;
  private List<DcsPlugin> dcs_plugins;
}
