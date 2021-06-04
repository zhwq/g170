package homework.week03.filter;

import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

/**
 * ip 放行名单 过滤
 * <p/>
 * RuleBasedIpFilter
 */
public class IpAcceptFilter implements IpFilterRule {

  static List<String> acceptIpList;
  //
  static {
    acceptIpList = Arrays.asList("localhost", "127.0.0.1");
  }

  @Override
  public boolean matches(InetSocketAddress remoteAddress) {
    final String host = remoteAddress.getHostString();
    System.out.println("===host: " + host);
    return !acceptIpList.contains(host);
  }

  @Override
  public IpFilterRuleType ruleType() {
    return IpFilterRuleType.ACCEPT;
  }

}
