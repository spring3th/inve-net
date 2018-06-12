package one.inve.cluster.gossip;

public interface GossipConfig {

  int getGossipFanout();

  long getGossipInterval();

  int getGossipRepeatMult();

}
