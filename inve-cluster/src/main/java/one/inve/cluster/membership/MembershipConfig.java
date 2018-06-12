package one.inve.cluster.membership;

import java.util.List;
import java.util.Map;

import one.inve.transport.Address;

public interface MembershipConfig {

  List<Address> getSeedMembers();

  Map<String, String> getMetadata();

  int getSyncInterval();

  int getSyncTimeout();

  String getSyncGroup();

  int getPingInterval();

  int getSuspicionMult();

  String getMemberHost();

  Integer getMemberPort();

}
